package GUI;

import Account.Account;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transfer extends  JFrame{
    private JTextField transfer;
    private JPanel mainPanel;
    private JLabel transferamount;
    private JButton confirm;
    private JTextField account;
    private JLabel transferid;
    private JLabel errorMessage;
    private JButton cancel;
    private Customer cus;
    private Account curAccount;
    private Transfer window;
    private ViewAccounts preW;


    public Transfer(Customer cus,  Account curAccount,ViewAccounts preW){
        this.setContentPane(mainPanel);
        this.pack();
        this.cus = cus;
        this.curAccount = curAccount;
        this.preW = preW;
        this.window =this;
        errorMessage.setVisible(false);
        this.buttonActions();
        this.setVisible(true);
        this.setSize(400,400);
    }

    public void buttonActions(){
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountName = account.getText();
                Float transAmount = Float.valueOf(transfer.getText());
                if(!(accountName.equalsIgnoreCase(curAccount.getType()))&&curAccount.getMoney()>transAmount&&transAmount>5){
                    if(accountName.equalsIgnoreCase("saving")&& cus.getSavingAcc()!= null){
                        cus.transfer(curAccount,transAmount, cus.getSavingAcc());
                        errorMessage.setText("Transfer Successful");
                        errorMessage.setVisible(true);
                        //转换汇率和update
                    }
                   else if(accountName.equalsIgnoreCase("checking") &&cus.getCheckingAcc()!=null){

                        cus.transfer(curAccount,transAmount, cus.getCheckingAcc());
                        errorMessage.setText("Transfer Successful");
                        errorMessage.setVisible(true);
                    }
                   else if(accountName.equalsIgnoreCase("security")&& cus.getSecurityAcc()!= null){
                        cus.transfer(curAccount,transAmount, cus.getSecurityAcc());
                        errorMessage.setText("Transfer Successful");
                        errorMessage.setVisible(true);
                    }
                   else{
                        errorMessage.setText("The account you want to transfer does not exist");
                        errorMessage.setVisible(true);
                    }
                }
                else{
                    errorMessage.setText("Invalid account or invalid transfer amount");
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
