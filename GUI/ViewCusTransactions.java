package GUI;

import Transaction.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewCusTransactions extends  JFrame{
    private JList display;
    private JPanel mainPanel;
    private JButton cancel;
    private ViewCusTransactions window;
    private CustomerTransactions preW;
    private ArrayList<Transaction> transactions;

    public ViewCusTransactions(CustomerTransactions preW, ArrayList<Transaction> transactions){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.bottonActions();
        this.transactions = transactions;
        this.display();
        this.setVisible(true);
        this.setSize(400,400);

    }

    public void bottonActions(){
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

        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        for (int i = 0; i < transactions.size(); i++) {
            String str = transactions.get(i).toString();
            listModel.addElement(str);
        }
    }
}
