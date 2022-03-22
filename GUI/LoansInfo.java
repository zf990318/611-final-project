package GUI;

import LoanStockCurrency.Loan;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoansInfo extends JFrame{
    private JButton cancel;
    private JPanel mainPanel;
    private JList display;
    private ViewLoans preW;
    private LoansInfo window;
    private Customer cus;

    public LoansInfo(ViewLoans preW, Customer cus){
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

    }
    public void display(){
        ArrayList<Loan> loans = cus.getLoans();
        DefaultListModel   model   =   new   DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel)(display.getModel());
        listModel.removeAllElements();
        for(int i = 0; i<loans.size();i++){
            if (loans.get(i).getConfirmed()==1){
                String str = loans.get(i).toString();
                listModel.addElement(str);
            }
        }
    }


}
