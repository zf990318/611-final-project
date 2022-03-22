package GUI;

import LoanStockCurrency.Loan;
import User.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ApprovedLoans extends JFrame{
    private JList display;
    private JPanel mainPanel;
    private JButton cancel;
    private ApprovedLoans window;
    private ManageLoans preW;
    private Manager mag;

    public ApprovedLoans(ManageLoans preW, Manager mag){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.mag=mag;
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

    public void display() {
        ArrayList<Loan> loans = mag.getApprovedLoan();
        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        for (int i = 0; i < loans.size(); i++) {
            String str = loans.get(i).toString();
            listModel.addElement(str);
        }
    }
}
