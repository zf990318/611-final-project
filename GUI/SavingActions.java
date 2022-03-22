package GUI;

import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavingActions extends JFrame{
    private JButton deposit;
    private JPanel mainPanel;
    private JButton payloan;
    private JButton transfer;
    private JButton cancel;
    private JList display;
    private SavingActions window;
    private ViewAccounts preW;
    private Customer cus;

    public SavingActions(ViewAccounts preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.cus = cus;
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

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Deposit(cus, cus.getSavingAcc(),preW);
            }
        });
        payloan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PayLoan(cus,cus.getSavingAcc(),preW);
            }
        });
        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transfer(cus,cus.getSavingAcc(),preW);
            }
        });
    }
    public void display(){
        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        String balance = String.valueOf(cus.getSavingAcc().getMoney());
        String bMessage = "Account Balance: " + balance;
        listModel.addElement(bMessage);
        String curType = cus.getSavingAcc().getCurrency();
        String cMessage = "Currency Type: " + curType;
        listModel.addElement(cMessage);
    }
}
