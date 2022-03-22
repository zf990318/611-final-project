package GUI;

import LoanStockCurrency.Loan;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckingActions extends JFrame{
    private JButton transfer;
    private JButton deposit;
    private JButton withdraw;
    private JButton cancel;
    private JPanel mainPanel;
    private JButton payloan;
    private JList display;
    private CheckingActions window;
    private ViewAccounts preW;
    private Customer cus;

    public CheckingActions(ViewAccounts preW,Customer cus){
        this.cus = cus;
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.display();
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

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transfer(cus,cus.getCheckingAcc(),preW);
            }
        });
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Withdraw(cus, cus.getCheckingAcc(),preW);
            }
        });
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Deposit(cus,cus.getCheckingAcc(),preW);
            }
        });
        payloan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PayLoan(cus,cus.getCheckingAcc(),preW);
            }
        });

    }

    public void display(){
        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        String balance = String.valueOf(cus.getCheckingAcc().getMoney());
        String bMessage = "Account Balance: " + balance;
        listModel.addElement(bMessage);
        String curType = cus.getCheckingAcc().getCurrency();
        String cMessage = "Currency Type: " + curType;
        listModel.addElement(cMessage);
    }
}
