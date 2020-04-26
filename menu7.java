import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class menu7 {
    private JLabel bgimg,user;
    private JPanel pl7;
    private JTextField mailfld;
    private JPasswordField passwordField;
    private JCheckBox checkBox;
    private JButton back,confirm,home;
    public static void updateCD(CustomerDetails pd){
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> columns = new ArrayList<>();
        columns.add("OrderID");
        data.add("getOrderID()");
        try{
            Database db = new Database();
            db.updateTable("CustomerDetails","Email","'"+pd.email+"'",data,columns);
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
    }
    String getLocation(String email){
        String result="";
        try{
            Database db = new Database();
            result=db.selectRow("CustomerDetails","Email",email,"Location");
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
        return result;
    }
    public menu7(){
        //Background image
        ImageIcon img=new ImageIcon(getClass().getResource("food2.png"));
        ImageIcon homepic=new ImageIcon(getClass().getResource("home.png"));
        bgimg=new JLabel(img);bgimg.setBounds(0,0,1000,720);
        pl7=new JPanel(null);pl7.setBounds(161,138,681,473);pl7.setBackground(new Color(255,255,255,90));
        ImageIcon img2=new ImageIcon(getClass().getResource("profile2.png"));
        user=new JLabel(img2);user.setBounds(495,250,60,60);
        //Text fields
        mailfld=new JTextField("Username (E-mail)");mailfld.setBounds(350,320,325,25);mailfld.setForeground(Color.gray);
        passwordField=new JPasswordField("Password");passwordField.setBounds(350,355,325,25);passwordField.setForeground(Color.gray);
        passwordField.setEchoChar((char)0);passwordField.setFocusable(true);
        checkBox=new JCheckBox("Show Password");checkBox.setBounds(350,385,130,25);
        checkBox.doClick();checkBox.setBackground(new Color(243,243,244));checkBox.setFont(new Font("Sheriff", Font.PLAIN, 10));
        //Buttons
        confirm=new JButton("Sign in");confirm.setBounds(465,420,128,25);confirm.setForeground(Color.white);confirm.setBackground(Color.black);
        back=new JButton("Create account");back.setBounds(465,450,128,25);back.setForeground(Color.white);back.setBackground(Color.black);
        home=new JButton();home.setIcon(homepic);home.setBounds(697,224,34,34);home.setForeground(Color.white);home.setBackground(Color.black);
        //Display 2 (After placing an order)
        if(menu2.total!=0){
            home.setVisible(false);
        }
        //focus listeners
        mailfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(mailfld.getText().equals("Username (E-mail)")){
                    mailfld.setText("");
                    mailfld.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(mailfld.getText().equals("")){
                    mailfld.setText("Username (E-mail)");
                    mailfld.setForeground(Color.gray);
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
        //button functions
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.frame.remove(bgimg);
                MainClass.frame.remove(back);MainClass.frame.remove(confirm);
                new menu4();
                MainClass.frame.revalidate();
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mailfld.getText().equals("Username (E-mail)") || passwordField.getText().equals("Password")){
                    JOptionPane.showMessageDialog(MainClass.frame,"Missing Fields");
                }
                else if(!menu4.checkDetails("CustomerDetails","Email",mailfld.getText().trim(),"Password").equals(passwordField.getText().trim())){
                    JOptionPane.showMessageDialog(MainClass.frame,"Incorrect Username/Password");
                }
                else if(menu2.total!=0){
                    MainClass.signedIn=true;
                    MainClass.userEmail=mailfld.getText().trim();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String crntime=dtf.format(now);
                    CustomerDetails pd = new CustomerDetails(mailfld.getText().trim(),"","",getLocation(mailfld.getText().trim()),menu2.total,menu4.deliverytime,"",crntime);
                    updateCD(pd);//Updates the OrderID in CustomerDetails table first
                    menu4.addtoODTable(pd);//Inserts to OrderDetails Table
                    menu4.addtoItemsOrdered(pd);//Inserts to Items Ordered Table
                    JOptionPane.showMessageDialog(MainClass.frame,"Thank you for your order.\nEstimated Delivery time: "+menu4.deliverytime+" minutes");
                    menu2.total=0;
                    MainClass.frame.remove(back);MainClass.frame.remove(confirm);MainClass.frame.remove(bgimg);
                    new menu1();
                    MainClass.frame.revalidate();
                }
                else{
                    MainClass.signedIn=true;
                    MainClass.userEmail=mailfld.getText().trim();
                    MainClass.frame.remove(back);MainClass.frame.remove(confirm);MainClass.frame.remove(bgimg);
                    new menu1();
                    MainClass.frame.revalidate();
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
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.frame.remove(back);MainClass.frame.remove(confirm);MainClass.frame.remove(bgimg);
                new menu1();
                MainClass.frame.revalidate();
            }
        });
        bgimg.add(home);bgimg.add(pl7);bgimg.add(user);
        bgimg.add(checkBox);bgimg.add(mailfld);bgimg.add(passwordField);
        MainClass.frame.add(back);MainClass.frame.add(confirm);
        MainClass.frame.add(bgimg);
    }
}