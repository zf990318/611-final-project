import GUI.LoginWindow;

import javax.swing.*;

public class Bank {
    public static void start(){
        JFrame frame = new LoginWindow("611 Bank ATM");
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
