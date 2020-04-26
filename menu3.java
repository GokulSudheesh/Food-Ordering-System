import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu3{
    static JPanel pl3;
    private JButton back,next,cancel;
    private JLabel lb1,lb2,lb3;
    public menu3(){
        ImageIcon exit=new ImageIcon(getClass().getResource("exit.png"));
        pl3=new JPanel(null);
        pl3.setBackground(Color.white);
        back=new JButton("<<");back.setBackground(Color.BLACK);back.setForeground(Color.white);back.setBounds(20,635,100,20);
        next=new JButton(">>");next.setBackground(Color.BLACK);next.setForeground(Color.white);next.setBounds(600,635,100,20);
        cancel=new JButton();cancel.setIcon(exit);cancel.setBackground(new Color(255,255,255,0));cancel.setBounds(935,10,34,34);
        lb1=new JLabel();
        lb1.setBounds(10,10,962,655);
        Border bd=BorderFactory.createLineBorder(Color.GRAY,9);
        lb1.setBorder(bd);
        lb2=new JLabel("Desserts/Drinks",SwingConstants.CENTER);
        Font f = new Font("TimesRoman",Font.PLAIN,20);
        lb2.setFont(f);
        lb2.setForeground(Color.white);
        lb2.setOpaque(true);
        lb2.setBackground(Color.black);
        lb2.setBounds(10,18,953,25);
        lb3=new JLabel();
        lb3.setBounds(700,20,262,640);
        Border bd1=BorderFactory.createLineBorder(Color.black);lb3.setBorder(bd1);

        menu2.displayMenuitems("Gulab Jamun",getClass().getResource("glbjamun.png"),12,40,50);
        int price12=menu2.getPrice("DS1","Desserts");
        menu2.displayMenuitems("Sizzling Brownie",getClass().getResource("szlbrownie.png"),13,265,50);
        int price13=menu2.getPrice("DS2","Desserts");
        menu2.displayMenuitems("Ice-Cream Fudge",getClass().getResource("fudge.png"),14,490,50);
        int price14=menu2.getPrice("DS3","Desserts");
        menu2.displayMenuitems("Falooda",getClass().getResource("falooda.png"),15,300,180,131,195);
        int price15=menu2.getPrice("DS4","Desserts");
        menu2.displayMenuitems("Pomegranate Juice",getClass().getResource("juice1.png"),16,40,405,176,198);
        int price16=menu2.getPrice("DS5","Desserts");
        menu2.displayMenuitems("Orange Juice",getClass().getResource("juice2.png"),17,276,405,176,198);
        int price17=menu2.getPrice("DS6","Desserts");
        menu2.displayMenuitems("Apple Juice",getClass().getResource("juice3.png"),18,492,405,176,198);
        int price18=menu2.getPrice("DS7","Desserts");

        JLabel lbb1=menu2.lb10;
        JLabel lbb2=menu2.lb11;
        lbb1.setBounds(705,600,100,15);
        lbb2.setBounds(785,600,100,15);
        menu2.sp[12].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                menu2.s[12]="\n"+"  Gulab Jamun"+"    "+menu2.sp[12].getValue()+"                      "+((int)menu2.sp[12].getValue()*price12);
                menu2.displayText(12,menu2.ind[12],price12,menu2.s[12]);
            }
        });
        menu2.sp[13].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                menu2.s[13]="\n"+"  Sizzling Brownie"+"    "+menu2.sp[13].getValue()+"                "+((int)menu2.sp[13].getValue()*price13);
                menu2.displayText(13,menu2.ind[13],price13,menu2.s[13]);
            }
        });
        menu2.sp[14].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                menu2.s[14]="\n"+"  Ice Cream Fudge"+"    "+menu2.sp[14].getValue()+"               "+((int)menu2.sp[14].getValue()*price14);
                menu2.displayText(14,menu2.ind[14],price14,menu2.s[14]);
            }
        });
        menu2.sp[15].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                menu2.s[15]="\n"+"  Falooda"+"    "+menu2.sp[15].getValue()+"                             "+((int)menu2.sp[15].getValue()*price15);
                menu2.displayText(15,menu2.ind[15],price15,menu2.s[15]);
            }
        });
        menu2.sp[16].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                menu2.s[16]="\n"+"  Pomegranate Juice"+"    "+menu2.sp[16].getValue()+"            "+((int)menu2.sp[16].getValue()*price16);
                menu2.displayText(16,menu2.ind[16],price16,menu2.s[16]);
            }
        });
        menu2.sp[17].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                menu2.s[17]="\n"+"  Orange Juice"+"    "+menu2.sp[17].getValue()+"                     "+((int)menu2.sp[17].getValue()*price17);
                menu2.displayText(17,menu2.ind[17],price17,menu2.s[17]);
            }
        });
        menu2.sp[18].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                menu2.s[18]="\n"+"  Apple Juice"+"    "+menu2.sp[18].getValue()+"                        "+((int)menu2.sp[18].getValue()*price18);
                menu2.displayText(18,menu2.ind[18],price18,menu2.s[18]);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pl3.setVisible(false);
                menu2.pl2.setVisible(true);
                menu2.pl2.add(menu2.ta1);
                menu2.pl2.add(menu2.lb10);
                menu2.pl2.add(menu2.lb11);
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(menu2.total==0){
                    JOptionPane.showMessageDialog(MainClass.frame,"No items were added.");
                }
                else{
                    MainClass.frame.remove(menu2.pl2);
                    MainClass.frame.remove(pl3);
                    new menu4();
                    MainClass.frame.revalidate();
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int response=JOptionPane.showConfirmDialog(MainClass.frame,"Do you want to cancel your order?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(response==JOptionPane.YES_OPTION){
                    MainClass.frame.remove(menu2.pl2);
                    MainClass.frame.remove(pl3);
                    new menu1();
                    MainClass.frame.revalidate();
                }
            }
        });
        pl3.add(cancel);
        pl3.add(lbb1);
        pl3.add(lbb2);
        pl3.add(lb1);
        pl3.add(lb2);
        pl3.add(lb3);
        pl3.add(back);
        pl3.add(next);
        pl3.add(menu2.ta1);
        for(int j=12;j<menu2.img.length;j++){
            pl3.add(menu2.img[j]);
            pl3.add(menu2.imgtxt[j]);
            pl3.add(menu2.sp[j]);
        }
        MainClass.frame.add(pl3);
    }
}