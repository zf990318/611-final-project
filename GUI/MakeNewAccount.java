package GUI;

import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeNewAccount extends  JFrame{
    private CustomerWindow preW;
    private JPanel mainPanel;
    private JButton checking;
    private JButton saving;
    private JButton security;
    private JButton cancel;
    private JLabel message;
    private JLabel errorMessage;
    private MakeNewAccount window;
    private Customer cus;

    public MakeNewAccount(CustomerWindow preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.cus = cus;
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

        security.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cus.getSecurityAcc()!=null){
                    errorMessage.setText("You already have a security account!");
                    errorMessage.setVisible(true);
                }
                else if(cus.getSavingAcc() == null||cus.getSavingAcc().getMoney() < 5000 ){
                    errorMessage.setText("Before you open a security account, your balance in saving account have to larger than 5000");
                    errorMessage.setVisible(true);
                }
                else {
                    window.dispose();
                    window.setVisible(false);
                    new NewAccountChoice(window, "Security", cus);
                }
            }
        });
        checking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cus.getCheckingAcc()!=null){
                    errorMessage.setText("You already have a checking account!");
                    errorMessage.setVisible(true);
                }
                else {
                    window.dispose();
                    window.setVisible(false);
                    new NewAccountChoice(window, "Checking", cus);
                }
            }
        });
        saving.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cus.getSavingAcc()!=null){
                    errorMessage.setText("You already have a saving account!");
                    errorMessage.setVisible(true);
                }
                else {
                    window.dispose();
                    window.setVisible(false);
                    new NewAccountChoice(window, "Saving", cus);
                }
            }
        });
    }
}
