package GUI;

import Account.Account;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Withdraw extends  JFrame{
    private JTextField withdraw;
    private JPanel mainPanel;
    private JLabel withdrawamount;
    private JButton confirm;
    private JLabel errorMessage;
    private JButton cancel;
    private Customer cus;
    private Account curAccount;
    private Withdraw window;
    private ViewAccounts preW;

    public Withdraw(Customer cus, Account curAccount,ViewAccounts preW){
        this.cus = cus;
        this.curAccount = curAccount;
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.buttonActions();
        errorMessage.setVisible(false);
        this.setVisible(true);
        this.setSize(400,400);
    }

    public void buttonActions(){
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float wAmount = Float.valueOf(withdraw.getText());
                if(wAmount > curAccount.getMoney()|| wAmount <5){
                    errorMessage.setText("Invalid WithDraw Amount");
                    errorMessage.setVisible(true);
                    //update
                }
                else{
                    cus.withdraw(curAccount,wAmount);
                    errorMessage.setText("WithDraw Successful");
                    errorMessage.setVisible(true);
                }

            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                preW.setVisible(true);
            }
        });
    }
}
