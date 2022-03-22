package GUI;

import Account.Account;
import Transaction.TransactionOP.DepositOP;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deposit extends JFrame{
    private JTextField deposit;
    private JPanel mainPanel;
    private JLabel depositamount;
    private JButton confirm;
    private JLabel errorMessage;
    private JButton cancel;
    private Account curAccount;
    private Customer cus;
    private ViewAccounts preW;
    private Deposit window;

    public Deposit(Customer cus,Account curAccount,ViewAccounts preW){
        this.setContentPane(mainPanel);
        this.pack();
        this.cus = cus;
        this.preW = preW;
        this.window = this;
        this.curAccount = curAccount;
        errorMessage.setVisible(false);
        this.buttonActions();
        this.setVisible(true);
        this.setSize(400,400);


    }

    public void buttonActions(){
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float dAmount = Float.valueOf(deposit.getText());
                if(dAmount.equals("")||dAmount <5){
                    errorMessage.setText("Deposit amount have to greater than 5");
                    errorMessage.setVisible(true);
                }
                else{
                    cus.deposit(curAccount, dAmount);
                    errorMessage.setText("Deposit Successful");
                    errorMessage.setVisible(true);
                    //update
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
