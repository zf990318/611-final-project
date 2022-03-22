package GUI;

import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseAccount extends  JFrame{
    private JPanel mainPanel;
    private JButton cancel;
    private JButton confirm;
    private JComboBox accounts;
    private JLabel message;
    private JLabel errorMessage;
    private CloseAccount window;
    private Customer cus;
    private CustomerWindow preW;
    private String drop_ac;

    public CloseAccount(CustomerWindow preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.cus = cus;
        this.ac_List();
        this.buttonActions();
        this.setVisible(true);
        message.setVisible(true);
        errorMessage.setVisible(false);
        this.setSize(400,400);


    }

    public void buttonActions(){
        accounts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = String.valueOf(accounts.getSelectedItem());
                drop_ac = str;
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
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drop_ac.equalsIgnoreCase("Checking")&&cus.getCheckingAcc().getMoney()>=5){
                    cus.deleteAccount("Checking");
                    errorMessage.setText("You have closed the checking account");
                    errorMessage.setVisible(true);
                }
                else if(drop_ac.equalsIgnoreCase("Saving")&&cus.getSavingAcc().getMoney()>=5){
                    cus.deleteAccount("Saving");
                    errorMessage.setText("You have closed the saving account");
                    errorMessage.setVisible(true);
                }
               else if(drop_ac.equalsIgnoreCase("Security")&& cus.getSecurityAcc().getMoney()>=5){
                    cus.deleteAccount("Security");
                    errorMessage.setText("You have closed the security");
                    errorMessage.setVisible(true);
                }
            }
        });
    }

    public void ac_List(){
        if(cus.getCheckingAcc()!=null){
            accounts.addItem("Checking");
        }
        if(cus.getSavingAcc()!=null){
            accounts.addItem("Saving");
        }
        if(cus.getSecurityAcc()!=null){
            accounts.addItem("Security");
        }
    }
}
