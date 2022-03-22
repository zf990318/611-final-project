package GUI;

import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerWindow extends JFrame{
    private JButton NewAccount;
    private JPanel mainPanel;
    private JButton loans;
    private JButton transactions;
    private JButton accounts;
    private JLabel wlecomeM;
    private JButton Logout;
    private JButton closeaccount;
    private CustomerWindow window;
    private LoginWindow preW;
    private Customer cus;

    public CustomerWindow(LoginWindow preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.cus = cus;
        this.window = this;
        this.preW = preW;
        this.bottonActions();
        this.setVisible(true);
        this.setSize(400,400);



    }

    public void bottonActions(){
        loans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new ViewLoans(window,cus);
            }
        });
        NewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new MakeNewAccount(window,cus);

            }
        });
        transactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new Transactions(window,cus);

            }
        });
        accounts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new ViewAccounts(window,cus);
            }
        });
        closeaccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new CloseAccount(window,cus);
            }
        });
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                preW.setVisible(true);
            }
        });

    }

}
