import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class menu2 {
    static JPanel pl2;
    private JLabel lb1,lb2,lb3;
    static JLabel lb10,lb11;
    static JLabel[] img;
    static JLabel[] imgtxt;
    static ArrayList<String> arr1;
    static JTextArea ta1;//
    static int count=0;
    static int[] ind;
    static  float total=0;
    static float[] t;
    static String[] s;
    static JSpinner sp[];
    private JButton next,cancel;
    public static void displayMenuitems(String text, URL filename, int index, int x, int y,int x1,int y1){
        ImageIcon img1=new ImageIcon(filename);
        img[index]=new JLabel(img1);img[index].setBounds(x,y,x1,y1);
        sp[index]=new JSpinner();sp[index].setBounds(x+150,y+y1+5,50,20);
        imgtxt[index]=new JLabel(text);imgtxt[index].setBounds(x,y+y1+5,200,15);
    }
    public static void displayMenuitems(String text, URL filename, int index, int x, int y){
        ImageIcon img1=new ImageIcon(filename);
        img[index]=new JLabel(img1);img[index].setBounds(x,y,181,101);
        sp[index]=new JSpinner();sp[index].setBounds(x+150,y+104,50,20);
        imgtxt[index]=new JLabel(text);imgtxt[index].setBounds(x,y+104,200,15);
    }
    public static void displayText(int i,int dd,int c,String ss){
        total-=t[i];
        t[i]=(int)sp[i].getValue()*c;
        total+=t[i];
        if((int)sp[i].getValue()>0){

            lb11.setText(Float.toString(total)+" Dhs");
            ta1.setText("");
            ta1.append("  Item               Qty                    Price");
            if (dd == -1) {
                arr1.add(0, "\nk");
                dd++;
            }
            arr1.remove(dd);
            arr1.add(ss);
            for (String value : arr1) {
                ta1.append(value);
            }
            for (int j = 0; j < ind.length; j++) {
                ind[j] = arr1.indexOf(s[j]);
            }
            System.out.println(ind[0] + " " + " " + ind[1] + " " + ind[2]);
            System.out.println(arr1);
        }
        else if((int)sp[i].getValue()==0 && dd!=-1){
            lb11.setText(Float.toString(total)+" Dhs");
            ta1.setText("");
            ta1.append("  Item               Qty                    Price");
            arr1.remove(dd);
            s[i]="";
            ind[i]=-1;
            for (String value : arr1) {
                ta1.append(value);
            }
            for (int j = 0; j < ind.length; j++) {
                ind[j] = arr1.indexOf(s[j]);
            }
        }
        else{
            sp[i].setValue(0);
        }
    }
    public static int getPrice(String foodID,String tableName){
        int price=0;
        try{
            Database db = new Database();
            price=Integer.parseInt(db.selectRow(tableName,"Food_ID",foodID,"Price"));
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
        return price;
    }
    public menu2(){
        s=new String[19];
        ind=new int[19];
        t=new float[19];
        for(int j=0;j<19;j++){
            s[j]="";
            t[j]=0;
            ind[j]=-1;
        }
        //Fonts
        Font f2=new Font("TimesRoman",Font.BOLD,15);
        Font f = new Font("TimesRoman",Font.PLAIN,20);

        img=new JLabel[19];
        imgtxt=new JLabel[19];
        sp=new JSpinner[19];
        pl2=new JPanel(null);
        pl2.setBackground(Color.WHITE);
        ImageIcon exit=new ImageIcon(getClass().getResource("exit.png"));
        next=new JButton(">>");next.setBackground(Color.BLACK);next.setForeground(Color.white);next.setBounds(600,635,100,20);
        cancel=new JButton();cancel.setIcon(exit);cancel.setBackground(new Color(255,255,255,0));cancel.setBounds(935,10,34,34);
        //Methods to add the labels(with spinners)
        displayMenuitems("Cheese balls (Servings: 4)",getClass().getResource("chsballs.png"),0,40,50);
        int price0=getPrice("MC1","MainCourse");
        displayMenuitems("Fried Chicken (Servings: 2)",getClass().getResource("kfc.png"),1,265,50);
        int price1=getPrice("MC2","MainCourse");
        displayMenuitems("Paneer Tikka (Servings: 3)",getClass().getResource("pnr.png"),2,490,50);
        int price2=getPrice("MC3","MainCourse");
        displayMenuitems("Chicken Biriyani",getClass().getResource("ckbr.png"),3,40,180);
        int price3=getPrice("MC4","MainCourse");
        displayMenuitems("Veg Biriyani",getClass().getResource("vgbr.png"),4,265,180);
        int price4=getPrice("MC5","MainCourse");
        displayMenuitems("Noodles(Veg/Non-Veg)",getClass().getResource("noodles.png"),5,490,180);
        int price5=getPrice("MC6","MainCourse");
        displayMenuitems("Masala Dosa",getClass().getResource("dosa.png"),6,40,310);
        int price6=getPrice("MC7","MainCourse");
        displayMenuitems("Pasta Bolognese",getClass().getResource("bologanesh.png"),7,265,310);
        int price7=getPrice("MC8","MainCourse");
        displayMenuitems("Chicken Roll",getClass().getResource("ckroll.png"),8,490,310);
        int price8=getPrice("MC9","MainCourse");
        displayMenuitems("Pizza Large (Veg/Non-Veg)",getClass().getResource("pizza.png"),9,40,440);
        int price9=getPrice("MC10","MainCourse");
        displayMenuitems("Club Sandwich",getClass().getResource("clbsnd.png"),10,265,440);
        int price10=getPrice("MC11","MainCourse");
        displayMenuitems("Burger (Veg/Non-Veg)",getClass().getResource("burger.png"),11,490,440);
        int price11=getPrice("MC12","MainCourse");
        lb10=new JLabel("Total: ");lb10.setBounds(705,600,100,15);lb10.setFont(f2);
        lb11=new JLabel("");lb11.setBounds(785,600,100,15);lb11.setFont(f2);
        lb1=new JLabel();
        lb1.setBounds(10,10,962,655);
        lb2=new JLabel("Main Course",SwingConstants.CENTER);
        lb3=new JLabel();
        lb3.setBounds(700,20,262,640);
        Border bd1=BorderFactory.createLineBorder(Color.black);lb3.setBorder(bd1);

        lb2.setFont(f);
        lb2.setForeground(Color.white);
        lb2.setOpaque(true);
        lb2.setBackground(Color.black);
        lb2.setBounds(10,18,953,25);
        Border bd=BorderFactory.createLineBorder(Color.GRAY,9);
        lb1.setBorder(bd);
        Font f1 = new Font("TimesRoman",Font.PLAIN,15);
        ta1=new JTextArea("  Item               Qty                    Price");ta1.setBounds(705,45,252,550);
        ta1.setFont(f1);ta1.setBackground(Color.lightGray);
        ta1.setEditable(false);
        arr1=new ArrayList<String>(19);
        sp[0].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[0]="\n"+"  Cheese Balls"+"    "+sp[0].getValue()+"                      "+((int)sp[0].getValue()*price0);
                displayText(0,ind[0],price0,s[0]);
            }
        });
        sp[1].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[1]="\n"+"  Fried Chicken"+"   "+sp[1].getValue()+"                      "+((int)sp[1].getValue()*price1);
                    displayText(1,ind[1],price1,s[1]);
            }
        });
        sp[2].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[2]="\n"+"  Paneer Tikka"+"    "+sp[2].getValue()+"                      "+((int)sp[2].getValue()*price2);
                    displayText(2,ind[2],price2,s[2]);
            }
        });
        sp[3].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[3]="\n"+"  Chicken Biriyani"+"    "+sp[3].getValue()+"                 "+((int)sp[3].getValue()*price3);
                    displayText(3,ind[3],price3,s[3]);
            }
        });
        sp[4].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[4]="\n"+"  Veg Biriyani"+"      "+sp[4].getValue()+"                      "+((int)sp[4].getValue()*price4);
                displayText(4,ind[4],price4,s[4]);
            }
        });
        sp[5].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[5]="\n"+"  Noodles"+"            "+sp[5].getValue()+"                      "+((int)sp[5].getValue()*price5);
                displayText(5,ind[5],price5,s[5]);
            }
        });
        sp[6].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[6]="\n"+"  Masala Dosa"+"    "+sp[6].getValue()+"                     "+((int)sp[6].getValue()*price6);
                displayText(6,ind[6],price6,s[6]);
            }
        });
        sp[7].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[7]="\n"+"  Pasta Bolognese"+"       "+sp[7].getValue()+"            "+((int)sp[7].getValue()*price7);
                displayText(7,ind[7],price7,s[7]);
            }
        });
        sp[8].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[8]="\n"+"  Chicken Roll"+"     "+sp[8].getValue()+"                      "+((int)sp[8].getValue()*price8);
                displayText(8,ind[8],price8,s[8]);
            }
        });
        sp[9].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[9]="\n"+"  Pizza"+"                "+sp[9].getValue()+"                     "+((int)sp[9].getValue()*price9);
                displayText(9,ind[9],price9,s[9]);
            }
        });
        sp[10].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[10]="\n"+"  Club Sandwich"+"  "+sp[10].getValue()+"                    "+((int)sp[10].getValue()*price10);
                displayText(10,ind[10],price10,s[10]);
            }
        });
        sp[11].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                s[11]="\n"+"  Burger"+"              "+sp[11].getValue()+"                      "+((int)sp[11].getValue()*price11);
                displayText(11,ind[11],price11,s[11]);
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(count==0){
                    count=1;
                    pl2.setVisible(false);
                    new menu3();
                    MainClass.frame.revalidate();
                }
                else {
                    pl2.setVisible(false);
                    menu3.pl3.setVisible(true);
                    menu3.pl3.add(ta1);
                    menu3.pl3.add(lb10);
                    menu3.pl3.add(lb11);
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int response=JOptionPane.showConfirmDialog(MainClass.frame,"Do you want to cancel your order?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(response==JOptionPane.YES_OPTION){
                    MainClass.frame.remove(pl2);
                    new menu1();
                    MainClass.frame.revalidate();
                }
            }
        });
        pl2.add(cancel);
        pl2.add(ta1);
        pl2.add(lb1);
        pl2.add(next);
        pl2.add(lb2);
        pl2.add(lb3);
        pl2.add(lb10);
        pl2.add(lb11);
        for(int j=0;j<12;j++){
            pl2.add(img[j]);
            pl2.add(imgtxt[j]);
            pl2.add(sp[j]);
        }
        MainClass.frame.add(pl2);
    }
}