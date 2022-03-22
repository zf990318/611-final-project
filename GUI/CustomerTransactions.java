package GUI;

import Transaction.Transaction;
import User.Manager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerTransactions extends  JFrame{
    private JTextField CustomerID;
    private JPanel mainPanel;
    private JLabel customerid;
    private JButton confirm;
    private JButton cancel;
    private CustomerTransactions window;
    private ManagerWindow preW;
    private Manager mag;

    public CustomerTransactions(ManagerWindow preW, Manager mag){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.mag=mag;
        this.bottonActions();
        this.setVisible(true);
        this.setSize(400,400);


    }

    public void  bottonActions(){
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
                String cusID = CustomerID.getText();
                ArrayList<Transaction> transactions = mag.viewTransactions(cusID);
                window.dispose();
                window.setVisible(false);
                new ViewCusTransactions(window,transactions);
            }
        });
    }
}
