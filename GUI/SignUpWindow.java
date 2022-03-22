package GUI;

import Database.Database;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpWindow extends  JFrame{
    private JTextField UserID;
    private JPasswordField Password;
    private JTextField Name;
    private JPanel mainPanel;
    private JLabel userid;
    private JLabel name;
    private JLabel password;
    private JButton cancel;
    private JButton confirm;
    private JLabel errorMessage;
    private SignUpWindow window;
    private LoginWindow preW;
    private Database db;

    public SignUpWindow(LoginWindow preW,Database db){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.db = db;
        errorMessage.setVisible(false);
        this.buttonActions();
        this.setVisible(true);
        this.setSize(400,400);

    }

    public void buttonActions(){
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                preW.setVisible(true);
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = UserID.getText();
                String password = Password.getText();
                String name = Name.getText();
                if (userID.equals("") || password.equals("") || name.equals("")) {
                    errorMessage.setText("User ID or Password or Name can not be null");
                    errorMessage.setVisible(true);
                }else {
                    Integer success = db.addUser(name, password, userID);
                    if (success==1){
                        Customer cus = (Customer) db.getUser(userID, 1);
                        errorMessage.setText("Successfully Opened a New Account");
                        errorMessage.setVisible(true);
                    }
                    else{
                        errorMessage.setText("Duplicate User Name.");
                        errorMessage.setVisible(true);
                    }
                }
        }
        });

    }
}
