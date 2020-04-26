import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import  java.lang.Math;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.jdesktop.swingx.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


public class menu5 {
    static JPanel pl5;
    private JLabel bgimg,totalprc,loginMsg,locationLabel;
    private JButton confirm,home;
    private JTextField namefld,mailfld,nmbfld;
    private JComboBox locationfld;
    private JCheckBox checkBox;
    private JPasswordField passwordField;
    static String name,email,number,location,password;
    private String UserID,OrderID,fullname,userEmail,phonenumber,userlocation,userPassword;
    private int status=0;//checks if there are any active orders
    private JLabel[] vector;
    private ImageIcon[] images={new ImageIcon(getClass().getResource("profile.png")),new ImageIcon(getClass().getResource("phone.png")),new ImageIcon(getClass().getResource("loc.png")),new ImageIcon(getClass().getResource("password.png"))};
    private ArrayList<String> Locations,Column,Data;
    private void load_details(){
        name=getAttributesCD("First_name")+" "+getAttributesCD("Last_name");
        userEmail=MainClass.userEmail;
        phonenumber=getAttributesCD("PhoneNumber");
        userlocation=getAttributesCD("Location");
        userPassword=getAttributesCD("Password");
    }
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
    public static int getStatus(String OrderID){
        int result=0;
        try{
            Database db = new Database();
            result= Integer.parseInt(db.selectRow("OrderDetails","OrderID",OrderID,"OrderComplete"));
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
        return result;
    }
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
    public static void updateCDTable(String columnstr,String datastr,String UserID){
        ArrayList<String> column=new ArrayList<String>();
        ArrayList<String> data=new ArrayList<String>();
        column.add(columnstr);
        data.add(datastr);
        try{
            Database db = new Database();
            db.updateTable("CustomerDetails","User_ID","'"+UserID+"'",data,column);
            db.con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public menu5(){
        //Loading all the data from SQL
        OrderID=getOrderID();
        UserID=getAttributesCD("User_ID");
        status=getStatus(OrderID);
        load_details();
        Column=new ArrayList<String>();
        Data=new ArrayList<String>();

        pl5=new JPanel(null);
        //bgcolor
        Color bgclr=new Color(253,255,230);
        //background image
        ImageIcon img=new ImageIcon(getClass().getResource("login.png"));
        ImageIcon homepic=new ImageIcon(getClass().getResource("home.png"));
        bgimg=new JLabel(img);bgimg.setBounds(0,0,1000,720);
        pl5.add(bgimg);
        //text fields
        namefld=new JTextField(name);namefld.setBounds(350,110,350,25);namefld.setForeground(Color.gray);
        mailfld=new JTextField(userEmail);mailfld.setBounds(350,155,350,25);mailfld.setForeground(Color.gray);
        nmbfld=new JTextField(phonenumber);nmbfld.setBounds(350,205,350,25);nmbfld.setForeground(Color.gray);
        locationfld=new JComboBox();locationfld.setBounds(350,275,200,25);locationfld.setForeground(Color.gray);
        locationfld.setEditable(true);loadLocation(Locations,locationfld);
        locationLabel=new JLabel(userlocation);locationLabel.setBounds(350,275,200,25);locationLabel.setForeground(Color.gray);
        locationLabel.setBackground(new Color(255,255,255));locationLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        locationLabel.setVisible(false);locationfld.setVisible(false);
        if(status==1){
            locationfld.setVisible(true);
            locationLabel.setVisible(false);
        }
        else{
            locationfld.setVisible(false);
            locationLabel.setVisible(true);
        }
        AutoCompleteDecorator.decorate(locationfld);
        passwordField=new JPasswordField("Password");passwordField.setBounds(350,350,350,25);passwordField.setForeground(Color.gray);
        passwordField.setEchoChar((char)0);passwordField.setFocusable(true);
        checkBox=new JCheckBox("Show Password");checkBox.setBounds(710,350,130,25);
        checkBox.doClick();checkBox.setBackground(bgclr);checkBox.setFont(new Font("Sheriff", Font.PLAIN, 10));
        //labels
        totalprc=new JLabel("Your total: "+menu2.total+" Dhs");totalprc.setBounds(350,380,150,20);
        loginMsg=new JLabel("Log in to an existing account");loginMsg.setBounds(430,460,200,20);
        //buttons
        confirm=new JButton("Update");confirm.setBounds(480,405,100,25);confirm.setForeground(Color.white);confirm.setBackground(Color.BLACK);
        home=new JButton();home.setIcon(homepic);home.setBounds(590,400,34,34);home.setForeground(Color.white);home.setBackground(Color.black);

        bgimg.add(namefld);bgimg.add(mailfld);bgimg.add(nmbfld);bgimg.add(locationfld);bgimg.add(checkBox);bgimg.add(locationLabel);
        bgimg.add(passwordField);bgimg.add(totalprc);bgimg.add(loginMsg);bgimg.add(confirm);bgimg.add(home);
        //vectors
        vector=new JLabel[4];
        displayvectors();
        //Action listeners
        namefld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(namefld.getText().equals(name)){
                    namefld.setText("");
                    namefld.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(namefld.getText().equals("")){
                    namefld.setText(name);
                    namefld.setForeground(Color.gray);
                }
            }
        });
        mailfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                mailfld.setForeground(Color.black);
                if(mailfld.getText().equals(userEmail)){
                    mailfld.setText("");
                    mailfld.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(mailfld.getText().equals("")){
                    mailfld.setText(userEmail);
                    mailfld.setForeground(Color.gray);
                }
            }
        });
        nmbfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                nmbfld.setForeground(Color.black);
                if(nmbfld.getText().equals(phonenumber)){
                    nmbfld.setText("");
                    nmbfld.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(nmbfld.getText().equals("")){
                    nmbfld.setText(phonenumber);
                    nmbfld.setForeground(Color.gray);
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
                if(namefld.getText().equals(mailfld.getText())){
                    JOptionPane.showMessageDialog(MainClass.frame,"Invalid Arguments.");
                }
                else if(!(mailfld.getText().contains("@") && mailfld.getText().contains(".com"))){
                    JOptionPane.showMessageDialog(MainClass.frame,"Invalid Arguments.");
                }
                else if(!mailfld.getText().trim().equals(userEmail)){
                    System.out.println("Yo1");
                    if(checkDetails("CustomerDetails","Email",mailfld.getText().trim(),"Email").equals(mailfld.getText().trim())){
                        System.out.println("Yo1");
                        mailfld.setForeground(Color.red);
                        JOptionPane.showMessageDialog(MainClass.frame,"Email address already taken. \nPlease try another or login to your account.");
                    }
                    else{
                        MainClass.userEmail=mailfld.getText().trim();
                        updateCDTable("Email","'"+mailfld.getText().trim()+"'",UserID);
                        load_details();
                        JOptionPane.showMessageDialog(MainClass.frame,"Email was updated successfully.");
                        mailfld.setForeground(Color.gray);
                    }
                }
                else if(!nmbfld.getText().trim().equals(phonenumber)){
                    System.out.println("Yo3");
                    if(checkDetails("CustomerDetails","PhoneNumber",nmbfld.getText().trim(),"PhoneNumber").equals(nmbfld.getText().trim())) {
                        nmbfld.setForeground(Color.red);
                        JOptionPane.showMessageDialog(MainClass.frame, "Phone number already taken. \nPlease try another or login to your account.");
                    }
                    else {
                        updateCDTable("PhoneNumber","'"+nmbfld.getText().trim()+"'",UserID);
                        load_details();
                        JOptionPane.showMessageDialog(MainClass.frame,"Phone number was updated successfully.");
                        nmbfld.setForeground(Color.gray);
                    }
                }
                else if(okay==1){
                    JOptionPane.showMessageDialog(MainClass.frame,"Invalid Arguments.");
                }
                else{
                    if(!namefld.getText().trim().equals(name)){
                        String fullname=namefld.getText().trim();
                        updateCDTable("First_name","'"+fullname.split(" ")[0]+"'",UserID);
                        updateCDTable("Last_name","'"+fullname.split(" ")[1]+"'",UserID);
                        load_details();
                        JOptionPane.showMessageDialog(MainClass.frame,"Name was updated successfully.");
                        namefld.setForeground(Color.gray);
                    }
                    if(!(locationfld.getSelectedItem().toString().equals(userlocation))){
                        if(!locationfld.getSelectedItem().toString().equals("-")){
                            updateCDTable("Location","'"+locationfld.getSelectedItem().toString().trim()+"'",UserID);
                            load_details();
                            JOptionPane.showMessageDialog(MainClass.frame,"Location was updated successfully.");
                        }
                    }
                    if(!(passwordField.getText().trim().equals(userPassword))){
                        if(!passwordField.getText().trim().equals("Password")){
                            updateCDTable("Password","'"+passwordField.getText().trim()+"'",UserID);
                            load_details();
                            passwordField.setText("Password");
                            passwordField.setEchoChar((char)0);
                            if(!checkBox.isSelected()){
                                checkBox.doClick();
                            }
                            JOptionPane.showMessageDialog(MainClass.frame,"Password was updated successfully.");
                        }
                    }
                }
            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.frame.remove(pl5);
                new menu1();
                MainClass.frame.revalidate();
            }
        });
        MainClass.frame.add(pl5);
    }
}