package GUI;
import Database.Database;
import User.Customer;
import User.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField UserName;
    private JButton signUp;
    private JPasswordField Password;
    private JLabel UserInput;
    private JLabel UserPassword;
    private JButton Login;
    private JLabel errorMessage;
    private LoginWindow window = this;
    private Database db = new Database();


    public LoginWindow(String title){
        super(title);
        this.setContentPane(mainPanel);
        this.pack();
        this.errorMessage.setVisible(false);
        this.buttonActions();

    }

    public void buttonActions(){
        Login.addActionListener(new ActionListener() {
            @Override /// Login listener
            public void actionPerformed(ActionEvent e) {
                //
                     String userName = UserName.getText();
                    String password = Password.getText();
                    Integer check = db.isValidUser(userName,password);
                    if (check == 1) {
                        Customer cus = (Customer) db.getUser(userName,check);
                        cus.setDB(db);
                        ///System.out.println(cus.getCheckingAcc().getUserName());
                        window.dispose();
                        errorMessage.setVisible(false);
                        window.setVisible(false);
                        new CustomerWindow(window, cus);
                        UserName.setText("");
                        Password.setText("");
                    } else if(check == 2){
                        Manager mag = db.getManager();
                        mag.setDB(db);
                        window.dispose();
                        errorMessage.setVisible(false);
                        window.setVisible(false);
                        new ManagerWindow(window, mag);
                        UserName.setText("");
                        Password.setText("");
                    }
                    else{
                        errorMessage.setVisible(true);
                        errorMessage.setText("User ID does not exist, Please try again");
                    }
                }

        });
        signUp.addActionListener(new ActionListener() {
            @Override /// Sign up listener
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new SignUpWindow(window,db);
                }
        });
            }




}
