package GUI;

import Account.Account;
import LoanStockCurrency.Loan;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PayLoan extends  JFrame{
    private JPanel mainPanel;
    private JTextField LoanID;
    private JLabel loanid;
    private JTextField PayAmount;
    private JLabel payamount;
    private JButton confirm;
    private JList display;
    private JLabel errorMessage;
    private JButton cancel;
    private Customer cus;
    private Account curAccount;
    private ViewAccounts preW;
    private PayLoan window;
    private Loan loan = null;

    public PayLoan(Customer cus,Account curAccount,ViewAccounts preW){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.buttonActions();
        this.setVisible(true);
        this.cus = cus;
        this. curAccount = curAccount;
        errorMessage.setVisible(false);
        this.display();
        this.setSize(500,500);
    }

    public void buttonActions(){
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loanId = LoanID.getText();
                Float payAmount = Float.valueOf(PayAmount.getText());
                ArrayList<Loan> loan_List = cus.getLoans();
                for(int i = 0; i< loan_List.size();i++){
                    if(loanId.equals(loan_List.get(i).getLoanID())){
                        loan = loan_List.get(i);
                    }
                }
                if(loanId == null || loan == null){
                    errorMessage.setText("Loan ID is not exist");
                    errorMessage.setVisible(true);
                }
                else if (payAmount > curAccount.getMoney()){
                    errorMessage.setText("Your account balance is not enough");
                    errorMessage.setVisible(true);
                }
                else{
                    errorMessage.setText("Payment Successful");
                    errorMessage.setVisible(true);
                    cus.payLoan(curAccount,loan,payAmount);

                    //更新数据库
                }

            }
        });

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
            String str = loans.get(i).toString();
            listModel.addElement(str);
        }
    }
}
