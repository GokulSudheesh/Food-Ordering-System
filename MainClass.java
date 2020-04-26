import javax.swing.*;

public class MainClass {
    static JFrame frame;
    public static boolean signedIn=false;
    public static String userEmail;
    public static void main(String[] args) {
        frame=new JFrame("Food Ordering");
        new menu1();
        frame.setSize(1000, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
