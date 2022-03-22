package GUI;

import LoanStockCurrency.Loan;
import User.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Profit extends  JFrame{
    private JList display;
    private JPanel mainPanel;
    private JButton cancel;
    private ManagerWindow preW;
    private Manager mag;
    private Profit window;

    public Profit(ManagerWindow preW, Manager mag){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.mag = mag;
        this.display();
        this.bottonActions();
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

    public void display(){
        String profit = String.valueOf(mag.getProfit());
        DefaultListModel   model   =   new   DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel)(display.getModel());
        listModel.removeAllElements();
        listModel.addElement("Manager profit: "+ profit);

    }
}
