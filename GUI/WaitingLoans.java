package GUI;

import LoanStockCurrency.Loan;
import User.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WaitingLoans extends  JFrame{
    private JList display;
    private JTextField LoanID;
    private JLabel loanid;
    private JPanel mainPanel;
    private JButton approve;
    private JButton cancel;
    private JLabel errorMessage;
    private WaitingLoans window;
    private ManageLoans preW;
    private Manager mag;
    private Integer index = -1;

    public WaitingLoans(ManageLoans preW, Manager mag){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.mag = mag;
        this.display();
        errorMessage.setVisible(false);
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
        approve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loanID = LoanID.getText();
                ArrayList<Loan> wList = mag.getUnapprovedLoan();
                for(int i=0; i<wList.size();i++){
                    if(wList.get(i).getLoanID().toString().equals(loanID)){
                        index = i;
                    }
                }
                if(index == -1){
                    errorMessage.setText("Invalid Loan ID");
                    errorMessage.setVisible(true);
                }
                else{
                    mag.confirmLoan(wList.get(index));
                    errorMessage.setVisible(true);
                    errorMessage.setText("Successfully approved the loan");
                }
            }
        });
    }

    public void display(){
        ArrayList<Loan> wList = mag.getUnapprovedLoan();
        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        for (int i = 0; i < wList.size(); i++) {
            String str = wList.get(i).toString();
            listModel.addElement(str);
        }

    }
}
