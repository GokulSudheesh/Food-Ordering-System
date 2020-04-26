import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu1 {
    private JPanel pl1;
    private JButton order,check,courier,exit;
    private JLabel bgimg;
    //private JLayeredPane layerPane;
    static  String data;
    //Dropdown menu (for the menu that scrolls down when clicked)
    private JLabel menuLabel,profilePic,message,emailMessage;
    private JButton dropMenu,signIn,signOut,update;
    int dropMenuval=0;
    public void dropdownMenuvisible(boolean val){
        menuLabel.setVisible(val);
        profilePic.setVisible(val);
        message.setVisible(val);
        if(MainClass.signedIn){
            update.setVisible(val);
            signOut.setVisible(val);
            emailMessage.setVisible(val);
            signIn.setVisible(false);
            message.setText("Signed in is as:");
            emailMessage.setText(MainClass.userEmail);
        }
        else{
            emailMessage.setVisible(false);
            signOut.setVisible(false);
            update.setVisible(false);
            signIn.setVisible(val);
            message.setText("Not signed in.");
        }

    }
    public  menu1(){
        //Background image
        ImageIcon img=new ImageIcon(getClass().getResource("food.png"));
        ImageIcon img2=new ImageIcon(getClass().getResource("profile2.png"));
        ImageIcon up=new ImageIcon(getClass().getResource("up-arrow.png"));
        ImageIcon down=new ImageIcon(getClass().getResource("down-arrow.png"));
        bgimg=new JLabel(img);
        bgimg.setBounds(0,0,1000,720);
        pl1=new JPanel(null);pl1.setBounds(305,134,418,441);pl1.setBackground(new Color(0,0,0,90));//lb1.setBackground(new Color(0, 0, 0,0));
        //Dropdown menu
        menuLabel=new JLabel();menuLabel.setBounds(790,5,190,200);menuLabel.setOpaque(true);
        menuLabel.setBackground(Color.white);menuLabel.setVisible(false);menuLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        profilePic=new JLabel(img2);
        profilePic.setBounds(860,15,60,60);profilePic.setVisible(false);
        message=new JLabel("");message.setBounds(840,75,100,30);message.setVisible(false);
        emailMessage=new JLabel("");emailMessage.setBounds(800,90,200,30);emailMessage.setVisible(false);
        update=new JButton("Update your info");update.setBounds(825,125,128,25);
        update.setBackground(Color.BLACK);update.setForeground(Color.white);update.setVisible(false);
        signIn=new JButton("Sign in");signIn.setBounds(825,160,128,25);
        signIn.setBackground(Color.BLACK);signIn.setForeground(Color.white);signIn.setVisible(false);
        signOut=new JButton("Sign out");signOut.setBounds(825,160,128,25);
        signOut.setBackground(Color.BLACK);signOut.setForeground(Color.white);signOut.setVisible(false);
        //Buttons
        JButton bt=new JButton();bt.setBounds(0,0,0,0);bgimg.add(bt);//don't remove
        dropMenu=new JButton();dropMenu.setIcon(down);
        dropMenu.setBounds(875,5,34,34);dropMenu.setBackground(new Color(255,255,255,100));//dropMenu.setBackground(Color.white);dropMenu.setForeground(Color.white);
        order=new JButton("New Order");
        order.setBounds(404,250,185,32);order.setBackground(Color.BLACK);order.setForeground(Color.white);
        check=new JButton("Review your Order");
        check.setBounds(404,302,185,32);check.setBackground(Color.BLACK);check.setForeground(Color.white);
        courier=new JButton("Register as a courier");
        courier.setBounds(404,354,185,32);courier.setBackground(Color.BLACK);courier.setForeground(Color.white);
        exit=new JButton("Exit");
        exit.setBounds(404,406,185,32);exit.setBackground(Color.BLACK);exit.setForeground(Color.white);
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.frame.remove(bgimg);
                new menu2();
                menu2.count=0;
                MainClass.frame.revalidate();
            }
        });
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(MainClass.signedIn){
                    MainClass.frame.remove(bgimg);
                    new menu6();
                    MainClass.frame.revalidate();
                }
                else{
                    MainClass.frame.remove(bgimg);
                    new menu7();
                    MainClass.frame.revalidate();
                }/*
                MainClass.frame.remove(bgimg);
                menu5.flag=0;
                new menu5();
                MainClass.frame.revalidate();*/

            }
        });
        courier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainClass.frame.remove(bgimg);
                new menu8();
                MainClass.frame.revalidate();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.frame.dispose();
            }
        });
        dropMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(dropMenuval%2==0){
                    dropdownMenuvisible(true);
                    dropMenu.setIcon(up);
                    dropMenu.setBounds(875,205,34,34);
                }
                else{
                    dropdownMenuvisible(false);
                    dropMenu.setIcon(down);
                    dropMenu.setBounds(875,5,34,34);
                }
                dropMenuval++;
            }
        });
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.frame.remove(bgimg);
                new menu7();
                MainClass.frame.revalidate();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainClass.frame.remove(bgimg);
                new menu5();
                MainClass.frame.revalidate();
            }
        });
        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainClass.signedIn=false;
                MainClass.userEmail="";
                dropMenu.doClick();
            }
        });
        //drop down menu
        bgimg.add(profilePic);bgimg.add(message);
        bgimg.add(emailMessage);bgimg.add(update);
        bgimg.add(signIn);bgimg.add(signOut);
        //Others
        bgimg.add(order);bgimg.add(check);bgimg.add(courier);bgimg.add(exit);
        bgimg.add(dropMenu);
        bgimg.add(pl1);bgimg.add(menuLabel);
        MainClass.frame.add(bgimg);
    }
}