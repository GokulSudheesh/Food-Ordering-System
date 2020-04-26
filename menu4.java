import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.*;
import  java.lang.Math;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.jdesktop.swingx.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


public class menu4 {
    static JPanel pl4;
    private JLabel bgimg,totalprc,loginMsg;
    private JButton confirm,login,cancel,home;
    private JTextField namefld,mailfld,nmbfld,landmark;
    private JComboBox locationfld;
    private JCheckBox checkBox;
    private JPasswordField passwordField;
    static String name,email,number,location,password;
    private JLabel[] vector;
    private ImageIcon[] images={new ImageIcon(getClass().getResource("profile.png")),new ImageIcon(getClass().getResource("phone.png")),new ImageIcon(getClass().getResource("loc.png")),new ImageIcon(getClass().getResource("password.png"))};
    static int deliverytime;
    private ArrayList<String> Locations;
    public static void loadLocation(ArrayList<String> Locations,JComboBox locationfld){
        try{
            Database db = new Database();
            Locations=db.selectColumn("Location","Area");
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
        locationfld.addItem("-");
        for (int i=0;i<Locations.size();i++){
            locationfld.addItem(Locations.get(i));
        }
    }
    private void displayvectors(){
        int x=270;int y=120;
        for(int j=0;j<4;j++) {
            vector[j] = new JLabel(images[j]);vector[j].setBounds(x,y,60,60);
            bgimg.add(vector[j]);
            y+=70;
        }
    }
    public static void addtoItemsOrdered(CustomerDetails pd){
        String OrderID="";
        try{
            Database db = new Database();
            OrderID= db.selectRow("CustomerDetails","Email",pd.email,"OrderID");
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }

        ItemDetails id=new ItemDetails();
        for (int i=0;i<menu2.arr1.size();i++){
            String str=menu2.arr1.get(i).trim().replaceAll("\\s+", ",");
            id.addtoItem(str);
            id.addtoPrice(str);
            id.addtoQuantity(str);
        }
        for (int i=0;i<menu2.arr1.size();i++){
            ArrayList<String> data = new ArrayList<>();
            data.add(OrderID+id.getItems().get(i));
            data.add(id.getItems().get(i));
            data.add(id.getQuantity().get(i));
            data.add(id.getPrice().get(i));
            try{
                Database db = new Database();
                db.insertData("ItemsOrdered",data);
                db.con.close();
            }
            catch (Exception e) { System.out.println(e); }
        }
    }
    public static void addtoCDTable(CustomerDetails pd){
        ArrayList<String> data = new ArrayList<>();
        data.add("'"+pd.userID+"'");data.add("'"+pd.firstname+"'");
        data.add("'"+pd.lastname+"'");data.add("'"+pd.email+"'");
        data.add("'"+pd.number+"'");data.add("'"+pd.location+"'");
        data.add("'"+pd.password+"'");data.add("getOrderID()");
        try{
            Database db = new Database();
            db.insertDataWFunc("CustomerDetails",data);
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
    }
    public static void addtoODTable(CustomerDetails pd){
        String OrderID;
        try{
            Database db = new Database();
            OrderID= db.selectRow("CustomerDetails","Email",pd.email,"OrderID");
            ArrayList<String> data = new ArrayList<>();
            data.add("findMax()");
            data.add("'"+OrderID+"'");//Primary key
            data.add("'"+pd.date+"'");
            data.add("'"+pd.time24+"'");//time of the order (24 hr format)
            data.add("'"+String.valueOf(pd.total)+"'");//total amount
            data.add("getVAT("+String.valueOf(pd.total)+")");//function in SQL to get VAT
            data.add("'"+String.valueOf(pd.time)+"'");//Estimated delivery time
            data.add("null");//Actual delivery time
            data.add("false");//Order Complete (Status)
            data.add("getCourierID("+"'"+pd.location+"')");//CourierID
            db.insertDataWFunc("OrderDetails",data);
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
    }
    public static String checkDetails(String tableName,String primaryKey,String primarykeyVal,String attribute){
        String sample="";
        try{
            Database db = new Database();
            sample=db.selectRow(tableName,primaryKey,primarykeyVal,attribute);
            db.con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return sample;
    }
    public menu4(){
        deliverytime=(int)(Math.random() * ((55 - 15) + 1)) + 15;
        pl4=new JPanel(null);
        //bgcolor
        Color bgclr=new Color(253,255,230);
        //background image
        ImageIcon img=new ImageIcon(getClass().getResource("login.png"));
        ImageIcon homepic=new ImageIcon(getClass().getResource("home.png"));
        bgimg=new JLabel(img);bgimg.setBounds(0,0,1000,720);
        pl4.add(bgimg);
        //text fields
        namefld=new JTextField("Name");namefld.setBounds(350,110,350,25);namefld.setForeground(Color.gray);
        mailfld=new JTextField("E-mail");mailfld.setBounds(350,155,350,25);mailfld.setForeground(Color.gray);
        nmbfld=new JTextField("Phone Number");nmbfld.setBounds(350,205,350,25);nmbfld.setForeground(Color.gray);
        locationfld=new JComboBox();locationfld.setBounds(350,275,200,25);locationfld.setForeground(Color.gray);
        locationfld.setEditable(true);loadLocation(Locations,locationfld);
        landmark=new JTextField("Landmark");landmark.setBounds(550,275,150,25);landmark.setForeground(Color.gray);
        AutoCompleteDecorator.decorate(locationfld);
        passwordField=new JPasswordField("Password");passwordField.setBounds(350,350,350,25);passwordField.setForeground(Color.gray);
        passwordField.setEchoChar((char)0);passwordField.setFocusable(true);
        checkBox=new JCheckBox("Show Password");checkBox.setBounds(710,350,130,25);
        checkBox.doClick();checkBox.setBackground(bgclr);checkBox.setFont(new Font("Sheriff", Font.PLAIN, 10));
        //labels
        totalprc=new JLabel("Your total: "+menu2.total+" Dhs");totalprc.setBounds(350,380,150,20);
        loginMsg=new JLabel("Log in to an existing account");loginMsg.setBounds(430,460,200,20);
        //buttons
        confirm=new JButton("Confirm");confirm.setBounds(480,405,100,25);confirm.setForeground(Color.white);confirm.setBackground(Color.BLACK);
        login=new JButton("Log in");login.setBounds(480,480,100,25);login.setForeground(Color.white);login.setBackground(Color.BLACK);
        cancel=new JButton("Cancel Order");cancel.setBounds(700,380,120,25);cancel.setForeground(Color.white);cancel.setBackground(Color.black);
        home=new JButton();home.setIcon(homepic);home.setBounds(590,400,34,34);home.setForeground(Color.white);home.setBackground(Color.black);
        //Display 2 (After placing an order)
        if(menu2.total!=0){
            home.setVisible(false);
        }

        bgimg.add(namefld);bgimg.add(mailfld);bgimg.add(nmbfld);bgimg.add(landmark);bgimg.add(locationfld);bgimg.add(checkBox);bgimg.add(cancel);
        bgimg.add(passwordField);bgimg.add(totalprc);bgimg.add(loginMsg);bgimg.add(confirm);bgimg.add(login);bgimg.add(home);
        //vectors
        vector=new JLabel[4];
        displayvectors();
        //Display 2 (For logging in)
        if(menu2.total==0){
            totalprc.setVisible(false);
            cancel.setVisible(false);
        }
        //Action listeners
        namefld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(namefld.getText().equals("Name")){
                    namefld.setText("");
                    namefld.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(namefld.getText().equals("")){
                    namefld.setText("Name");
                    namefld.setForeground(Color.gray);
                }
            }
        });
        mailfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                mailfld.setForeground(Color.black);
                if(mailfld.getText().equals("E-mail")){
                    mailfld.setText("");
                    mailfld.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(mailfld.getText().equals("")){
                    mailfld.setText("E-mail");
                    mailfld.setForeground(Color.gray);
                }
            }
        });
        nmbfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                nmbfld.setForeground(Color.black);
                if(nmbfld.getText().equals("Phone Number")){
                    nmbfld.setText("");
                    nmbfld.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(nmbfld.getText().equals("")){
                    nmbfld.setText("Phone Number");
                    nmbfld.setForeground(Color.gray);
                }
            }
        });
        landmark.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(landmark.getText().equals("Landmark")){
                    landmark.setText("");
                    landmark.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(landmark.getText().equals("")){
                    landmark.setText("Landmark");
                    landmark.setForeground(Color.gray);
                }
            }
        });
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(passwordField.getText().equals("Password")){
                    if(checkBox.isSelected()){
                        checkBox.doClick();
                    }
                    passwordField.setText("");
                    passwordField.setEchoChar(('*'));
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(passwordField.getText().equals("")){
                    if(!checkBox.isSelected()){
                        checkBox.doClick();
                    }
                    passwordField.setText("Password");
                    passwordField.setEchoChar((char)0);
                }
            }
        });
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(checkBox.isSelected()){
                    passwordField.setEchoChar((char)0);
                }
                else{
                    passwordField.setEchoChar('*');
                }
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int okay=0;//for number field (accepts only numeric value)
                char[] num=nmbfld.getText().toCharArray();
                for(char c:num){
                    if(!(Character.isDigit(c))){
                        okay=1;
                    }
                }
                if(namefld.getText().equals("Name") || mailfld.getText().equals("E-mail") || nmbfld.getText().equals("Phone Number") || passwordField.getText().equals("") || passwordField.getText().equals("Password")){
                    JOptionPane.showMessageDialog(MainClass.frame,"Missing Fields.");
                }
                else if(namefld.getText().equals(mailfld.getText())){
                    JOptionPane.showMessageDialog(MainClass.frame,"Invalid Arguments.");
                }
                else if(!(mailfld.getText().contains("@") && mailfld.getText().contains(".com"))){
                    JOptionPane.showMessageDialog(MainClass.frame,"Invalid Arguments.");
                }
                else if(checkDetails("CustomerDetails","Email",mailfld.getText().trim(),"Email").equals(mailfld.getText().trim())){
                        mailfld.setForeground(Color.red);
                        JOptionPane.showMessageDialog(MainClass.frame,"Email address already taken. \nPlease try another or login to your account.");
                }
                else if(checkDetails("CustomerDetails","PhoneNumber",nmbfld.getText().trim(),"PhoneNumber").equals(nmbfld.getText().trim())){
                    nmbfld.setForeground(Color.red);
                    JOptionPane.showMessageDialog(MainClass.frame,"Phone number already taken. \nPlease try another or login to your account.");
                }
                else if(okay==1){
                    JOptionPane.showMessageDialog(MainClass.frame,"Invalid Arguments.");
                }
                else if(menu2.total!=0){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String crntime=dtf.format(now);
                    name=namefld.getText().trim();email=mailfld.getText().trim();number=nmbfld.getText().trim();
                    location=locationfld.getSelectedItem().toString();password=passwordField.getText().trim();
                    JOptionPane.showMessageDialog(MainClass.frame,"Thank you for your order.\nEstimated Delivery time: "+deliverytime+" minutes");
                    CustomerDetails pd=new CustomerDetails(email,name,number,location,menu2.total,deliverytime,password,crntime);
                    addtoCDTable(pd);//inserting customer details into CustomerDetails table in MySQL
                    addtoODTable(pd);//inserting into Order Details table in MySQL
                    addtoItemsOrdered(pd);//inserting into ItemOrdered table

                    MainClass.frame.remove(pl4);
                    menu2.total=0;
                    MainClass.signedIn=true;
                    MainClass.userEmail=email;
                    new menu1();
                    MainClass.frame.revalidate();
                }
                else{
                    name=namefld.getText().trim();email=mailfld.getText().trim();number=nmbfld.getText().trim();
                    location=locationfld.getSelectedItem().toString();password=passwordField.getText().trim();
                    CustomerDetails pd=new CustomerDetails(email,name,number,location,menu2.total,deliverytime,password,"");
                    addtoCDTable(pd);//inserting customer details into CustomerDetails table in MySQL
                    MainClass.frame.remove(pl4);
                    menu2.total=0;
                    MainClass.signedIn=true;
                    MainClass.userEmail=email;
                    new menu1();
                    MainClass.frame.revalidate();
                }
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.frame.remove(pl4);
                new menu7();
                MainClass.frame.revalidate();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int response=JOptionPane.showConfirmDialog(MainClass.frame,"Do you want to cancel your order?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(response==JOptionPane.YES_OPTION){
                    MainClass.frame.remove(pl4);
                    menu2.total=0;
                    new menu1();
                    MainClass.frame.revalidate();
                }
            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.frame.remove(pl4);
                new menu1();
                MainClass.frame.revalidate();
            }
        });
        MainClass.frame.add(pl4);
    }
}