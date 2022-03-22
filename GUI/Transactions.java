package GUI;

import LoanStockCurrency.Loan;
import Transaction.Transaction;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Transactions extends JFrame {
    private JPanel mainPanel;
    private JButton cancel;
    private JList display;
    private Transactions window;
    private CustomerWindow preW;
    private JFrame f = new JFrame();
    private Customer cus;

    public Transactions(CustomerWindow preW, Customer cus) {
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.cus = cus;
        this.display();
        this.buttonActions();
        this.setVisible(true);
        this.setSize(400, 400);

    }

    public void buttonActions() {
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                preW.setVisible(true);
            }
        });

    }

    public void display() {
        ArrayList<String> transactions = cus.viewAllTransactions();
        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        for (int i = 0; i < transactions.size(); i++) {
            String str = transactions.get(i);
            listModel.addElement(str);
        }
    }
}
