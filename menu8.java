import org.jdesktop.swingx.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.nio.file.LinkOption;
import java.util.ArrayList;

public class menu8 {

    static JPanel pl8;
    private JTextField empIdfld, namefld, landmark, vehiclemodelfld, regnumfld, emailfld;
    private JTextField phonenumfld;
    private JLabel backimg;
    private JButton confirmbtn,home;
    private JComboBox locationfld;
    ArrayList <String> Locations;

    private String name, email, empId, location, phoneNo, vmodel, regNo;

    private JLabel[] vector;
    private ImageIcon[] images={new ImageIcon(getClass().getResource("userCOUR.png")),new ImageIcon(getClass().getResource("locCOUR.png")),new ImageIcon(getClass().getResource("vehicleCOUR.png")),new ImageIcon(getClass().getResource("phoneCOUR.png"))};;
    private void displayvectors(){
        int x=20;int y=140;
        for(int j=0;j<4;j++) {
            vector[j] = new JLabel(images[j]);vector[j].setBounds(x,y,60,60);
            backimg.add(vector[j]);
            if(j==0){
                y+=100;
            }
            else{
                y+=70;
            }
        }
    }

    public String checkDetails(String tableName,String primaryKey,String primarykeyVal,String attribute){
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

    public void addtoEDTable(EmployeeDetails pd){
        ArrayList<String> data = new ArrayList<>();
        data.add(pd.empId);data.add(pd.firstName);
        data.add(pd.lastName);data.add(pd.email);
        data.add(pd.phoneNo);data.add(pd.location);
        data.add(pd.vmodel);data.add(pd.regNo);
        try{
            Database db = new Database();
            db.insertData("CourierDetails",data);
            db.con.close();
        }
        catch (Exception e) { System.out.println(e); }
    }

    public menu8(){
        ImageIcon image = new ImageIcon(getClass().getResource("courierLogin.png"));
        backimg = new JLabel(image);
        backimg.setBounds(0,0,1000,720);
        pl8 = new JPanel(null);


        namefld=new JTextField("Name");
        namefld.setBounds(100,110,350,25);
        namefld.setForeground(Color.GRAY);

        emailfld=new JTextField("Email");
        emailfld.setBounds(100,155,350,25);
        emailfld.setForeground(Color.gray);

        empIdfld=new JTextField("Employee ID");
        empIdfld.setBounds(100,205,350,25);
        empIdfld.setForeground(Color.gray);

        locationfld=new JComboBox();
        locationfld.setBounds(100,250,200,25);
        locationfld.setForeground(Color.gray);
        locationfld.setEditable(true);
        menu4.loadLocation(Locations,locationfld);
        AutoCompleteDecorator.decorate(locationfld);

        landmark=new JTextField("Landmark");
        landmark.setBounds(310,250,150,25);
        landmark.setForeground(Color.gray);

        regnumfld=new JTextField("Vehicle Registration No.");
        regnumfld.setBounds(100,300,350,25);
        regnumfld.setForeground(Color.gray);

        vehiclemodelfld=new JTextField("Vehicle Model");
        vehiclemodelfld.setBounds(100,350,350,25);
        vehiclemodelfld.setForeground(Color.gray);

        phonenumfld=new JTextField("Phone no.");
        phonenumfld.setBounds(100,400,350,25);
        phonenumfld.setForeground(Color.gray);

        confirmbtn = new JButton("Confirm");
        confirmbtn.setBounds(215,450,100,25);
        confirmbtn.setForeground(Color.white);
        confirmbtn.setBackground(Color.black);

        ImageIcon homepic=new ImageIcon(getClass().getResource("home.png"));
        home=new JButton();home.setIcon(homepic);
        home.setBounds(325,445,34,34);
        home.setForeground(Color.white);
        home.setBackground(Color.black);

        MainClass.frame.add(pl8);
        pl8.add(backimg);
        backimg.add(namefld);
        backimg.add(emailfld);
        backimg.add(empIdfld);
        backimg.add(locationfld);
        backimg.add(landmark);
        backimg.add(regnumfld);
        backimg.add(vehiclemodelfld);
        backimg.add(phonenumfld);
        backimg.add(confirmbtn);
        backimg.add(home);
        vector=new JLabel[4];
        displayvectors();


        namefld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(namefld.getText().equals("Name")){
                    namefld.setText("");
                    namefld.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(namefld.getText().equals("")){
                    namefld.setText("Name");
                    namefld.setForeground(Color.GRAY);
                }
            }
        });

        emailfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                emailfld.setForeground(Color.black);
                if(emailfld.getText().equals("Email")){
                    emailfld.setText("");
                    emailfld.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(emailfld.getText().equals("")){
                    emailfld.setText("Email");
                    emailfld.setForeground(Color.GRAY);
                }
            }
        });


        empIdfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                empIdfld.setForeground(Color.black);
                if(empIdfld.getText().equals("Employee ID")){
                    empIdfld.setText("");
                    empIdfld.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(empIdfld.getText().equals("")){
                    empIdfld.setText("Employee ID");
                    empIdfld.setForeground(Color.GRAY);
                }
            }
        });

        landmark.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(landmark.getText().equals("Landmark")){
                    landmark.setText("");
                    landmark.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(landmark.getText().equals("")){
                    landmark.setText("Landmark");
                    landmark.setForeground(Color.GRAY);
                }
            }
        });

        regnumfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(regnumfld.getText().equals("Vehicle Registration No.")){
                    regnumfld.setText("");
                    regnumfld.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(regnumfld.getText().equals("")){
                    regnumfld.setText("Vehicle Registration No.");
                    regnumfld.setForeground(Color.GRAY);
                }
            }
        });

        vehiclemodelfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(vehiclemodelfld.getText().equals("Vehicle Model")){
                    vehiclemodelfld.setText("");
                    vehiclemodelfld.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(vehiclemodelfld.getText().equals("")){
                    vehiclemodelfld.setText("Vehicle Model");
                    vehiclemodelfld.setForeground(Color.GRAY);
                }
            }
        });

        phonenumfld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                phonenumfld.setForeground(Color.black);
                if(phonenumfld.getText().equals("Phone no.")){
                    phonenumfld.setText("");
                    phonenumfld.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(phonenumfld.getText().equals("")){
                    phonenumfld.setText("Phone no.");
                    phonenumfld.setForeground(Color.GRAY);
                }
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainClass.frame.remove(pl8);
                new menu1();
                MainClass.frame.revalidate();
            }
        });

        confirmbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int okay=0;//for number field (accepts only numeric value)
                char[] num=phonenumfld.getText().toCharArray();
                for(char c:num){
                    if(!(Character.isDigit(c))){
                        okay=1;
                    }
                }
                if(namefld.getText().equals("Name") || emailfld.getText().equals("E-mail") || phonenumfld.getText().equals("Phone Number") || empIdfld.getText().equals("Employee ID") || vehiclemodelfld.getText().equals("Vehicle Model") || regnumfld.getText().equals("Vehicle Registration No.") )
                {
                    JOptionPane.showMessageDialog(MainClass.frame,"Missing Fields.");
                }
                else if(namefld.getText().equals(emailfld.getText())){
                    JOptionPane.showMessageDialog(MainClass.frame,"Invalid Arguments.");
                }
                else if(!(emailfld.getText().contains("@") && emailfld.getText().contains(".com"))){
                    JOptionPane.showMessageDialog(MainClass.frame,"Invalid Arguments.");
                }
                else if(checkDetails("CourierDetails","Emp_ID",empIdfld.getText().trim(),"Emp_ID").equals(empIdfld.getText().trim())){
                    empIdfld.setForeground(Color.red);
                    JOptionPane.showMessageDialog(MainClass.frame,"This account is already registered. \nPlease try another.");
                }
                else if(checkDetails("CourierDetails","Email",emailfld.getText().trim(),"Email").equals(emailfld.getText().trim())){
                    emailfld.setForeground(Color.red);
                    JOptionPane.showMessageDialog(MainClass.frame,"This account is already registered. \nPlease try another.");
                }
                else if(checkDetails("CourierDetails","PhoneNumber",phonenumfld.getText().trim(),"PhoneNumber").equals(phonenumfld.getText().trim())){
                    phonenumfld.setForeground(Color.red);
                    JOptionPane.showMessageDialog(MainClass.frame,"This account is already registered. \\nPlease try another.");
                }
                else if(okay==1){
                    JOptionPane.showMessageDialog(MainClass.frame,"Invalid Arguments.");
                }
                else{
                    name=namefld.getText().trim();email=emailfld.getText().trim();phoneNo=phonenumfld.getText().trim();
                    location=locationfld.getSelectedItem().toString(); empId=empIdfld.getText().trim(); vmodel=vehiclemodelfld.getText().trim();
                    regNo=regnumfld.getText().trim();
                    EmployeeDetails pd=new EmployeeDetails(email,name,phoneNo,location,empId,vmodel,regNo);
                    addtoEDTable(pd);//inserting employee details into CustomerDetails table in MySQL
                    JOptionPane.showMessageDialog(MainClass.frame,"Data has been successfully recorded.");
                    MainClass.frame.remove(pl8);
                    new menu1();
                    MainClass.frame.revalidate();
                }
            }
        });
    }
}
