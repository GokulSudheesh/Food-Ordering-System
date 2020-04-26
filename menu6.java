import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class menu6 {
    private JLabel datentime,bgimg,name,mail,number,location,dlvrytime,comment;
    private JTextArea ta,addcomment;
    private JButton back,commentbtn;
    private String OrderID;
    private JRadioButton positive,negative;
    private ArrayList<String> items,quantity,price;
    public static  String getOrderID(){
        String OrderID="";
        try{
            Database db = new Database();
            OrderID= db.selectRow("CustomerDetails","Email",MainClass.userEmail,"OrderID");
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
        return OrderID;
    }
    public static String getAttributesCD(String column){
        String result="";
        try{
            Database db = new Database();
            result= db.selectRow("CustomerDetails","Email",MainClass.userEmail,column);
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
        return result;
    }
    public static String getAttributesOD(String column,String OrderID){
        String result="";
        try{
            Database db = new Database();
            result= db.selectRow("OrderDetails","OrderID",OrderID,column);
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
        return result;
    }
    ArrayList<String> getItems(String column){
        ArrayList<String> result = new ArrayList<>();
        try{
            Database db = new Database();
            result= db.selectColumn("ItemsOrdered",column);
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
        return  result;
    }
    void addComment(String OrderID,String commentText,boolean isComplaint,boolean isPraise){
        ArrayList<String> data = new ArrayList<>();
        data.add("'"+OrderID+"'");
        data.add("'"+commentText+"'");
        data.add(""+isComplaint);
        data.add(""+isPraise);
        try{
            Database db = new Database();
            db.insertDataWFunc("Comments",data);
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
    }
    String getComment(String OrderID){
        String result="";
        try{
            Database db = new Database();
            result=db.selectRow("Comments","OrderID",OrderID,"comment_text");
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
        return  result;
    }
    void updateComment(String OrderID,String text){
        ArrayList<String> column=new ArrayList<>();
        ArrayList<String> comment=new ArrayList<>();
        column.add("comment_text");
        comment.add("'"+text+"'");
        try{
            Database db = new Database();
            db.updateTable("Comments","OrderID","'"+OrderID+"'",comment,column);
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
    }
    public menu6(){
        OrderID=getOrderID();
        //Font
        Font f=new Font("Serif",Font.PLAIN,16);
        //Background image
        ImageIcon img=new ImageIcon(getClass().getResource("food3.png"));
        bgimg=new JLabel(img);bgimg.setBounds(0,0,1000,720);
        //Labels
        datentime=new JLabel(getAttributesOD("Date",OrderID)+"        "+getAttributesOD("Time",OrderID));
        datentime.setBounds(180,120,250,25);
        datentime.setFont(f);

        name=new JLabel("Name: "+getAttributesCD("First_name")+" "+getAttributesCD("Last_name"));
        name.setBounds(180,160,250,25);
        name.setFont(f);

        mail=new JLabel("E-mail: "+MainClass.userEmail);
        mail.setBounds(180,195,250,25);
        mail.setFont(f);

        number=new JLabel("Phone Number: "+getAttributesCD("PhoneNumber"));
        number.setBounds(180,230,250,25);
        number.setFont(f);

        location=new JLabel("Location: "+getAttributesCD("Location"));
        location.setBounds(180,265,300,25);
        location.setFont(f);

        dlvrytime=new JLabel("Delivery Time: "+getAttributesOD("estimated_DT",OrderID)+" mins");
        dlvrytime.setBounds(180,295,300,25);
        dlvrytime.setFont(f);

        comment=new JLabel("Comment: ");
        comment.setBounds(170,330,100,25);

        items=getItems("Item");
        quantity=getItems("Quantity");
        price=getItems("Price");
        //Checkbox
        positive=new JRadioButton("Positive review");
        positive.setBounds(170,510,130,25);
        positive.setBackground(Color.white);

        negative=new JRadioButton("Negative review");
        negative.setBounds(310,510,130,25);
        negative.setBackground(Color.white);
        ButtonGroup G = new ButtonGroup();
        G.add(positive);
        G.add(negative);
        //TextArea
        addcomment=new JTextArea();addcomment.setBounds(170,350,405,150);
        addcomment.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        addcomment.setBackground(Color.white);

        ta=new JTextArea();ta.setBounds(575,120,252,465);
        ta.setBackground(Color.white);
        ta.setFont(f);ta.setEditable(false);

        for(int j=0;j<items.size();j++){
            ta.append(items.get(j)+" x"+quantity.get(j)+"   "+price.get(j));
            ta.append("\n");
        }
        float total=Float.parseFloat(getAttributesOD("totalAmount",OrderID))+Float.parseFloat(getAttributesOD("vat",OrderID));
        ta.append("\t"+"Total: "+total+" Dhs");
        //Buttons
        back=new JButton("<<");
        back.setBounds(155,560,75,25);
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.frame.remove(bgimg);
                new menu1();
                MainClass.frame.revalidate();
            }
        });
        commentbtn=new JButton("Add");
        commentbtn.setBounds(500,510,75,25);
        commentbtn.setForeground(Color.white);
        commentbtn.setBackground(Color.black);
        commentbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addcomment.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(MainClass.frame,"Add your comment and try again.");
                }
                else if(!(positive.isSelected() || negative.isSelected())){
                    JOptionPane.showMessageDialog(MainClass.frame,"Please select the review type (Positive/Negative)");
                }
                else{
                    boolean isComplaint=false,isPraise=false;
                    if(positive.isSelected()){
                        isPraise=true;
                    }
                    else{
                        isComplaint=true;
                    }
                    String presentText=getComment(OrderID);
                    if(presentText.equals("")){
                        addComment(OrderID,addcomment.getText().trim(),isComplaint,isPraise);
                    }
                    else {
                        updateComment(OrderID,presentText+addcomment.getText().trim());
                    }
                    JOptionPane.showMessageDialog(MainClass.frame,"Your review was successfully recorded.\nThank you.");
                    addcomment.setText("");
                }
            }
        });
        bgimg.add(datentime);
        bgimg.add(name);
        bgimg.add(mail);
        bgimg.add(number);
        bgimg.add(location);
        bgimg.add(dlvrytime);
        bgimg.add(ta);
        bgimg.add(addcomment);
        bgimg.add(comment);
        bgimg.add(back);
        bgimg.add(commentbtn);
        bgimg.add(positive);
        bgimg.add(negative);
        MainClass.frame.add(bgimg);
    }
}
