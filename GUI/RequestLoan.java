package GUI;

import LoanStockCurrency.Loan;
import LoanStockCurrency.LoanProduct;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RequestLoan extends JFrame{
    private JPanel mainPanel;
    private JTextField loanamout;
    private JButton confirm;
    private JLabel loanType;
    private JLabel loanAmount;
    private JButton cancel;
    private JComboBox loantype;
    private JLabel loanaccount;
    private JLabel errorMessage;
    private RequestLoan window;
    private ViewLoans preW;
    private String loan;
    private Customer cus;
    private ArrayList<String> loan_list;
    private LoanProduct loan_name;
    private JTextField LoanAccount;


    public RequestLoan(ViewLoans preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.cus = cus;
        this.loan_list = this.loan_p();
        this.buttonActions();
        this.setVisible(true);
        errorMessage.setVisible(false);
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
        loantype.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<LoanProduct> loanList = cus.getLoanProduct();
                String s = (String) loantype.getSelectedItem();
                loan = s;
                System.out.println(loan);
                for(int i =0; i<loanList.size();i++){
                    if(loan.equals(loanList.get(i).toString())){
                        loan_name = loanList.get(i);
                    }
                }
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float loan_amount = Float.valueOf(loanamout.getText());
                String ac = LoanAccount.getText();
                System.out.println(loan);
                if(check()==(false)){
                    errorMessage.setVisible(true);
                    errorMessage.setText("You have already requested this loan");
                }
                else{
                    if(ac.equalsIgnoreCase("saving")&&cus.getSavingAcc()!=null){
                        cus.requestLoan(loan_name,loan_amount, cus.getSavingAcc());
                        errorMessage.setText("Successfully requested the loan");
                        errorMessage.setVisible(true);
                    }
                    else if (ac.equalsIgnoreCase("checking")&&cus.getCheckingAcc()!=null){
                        cus.requestLoan(loan_name,loan_amount, cus.getCheckingAcc());
                        errorMessage.setText("Successfully requested the loan");
                        errorMessage.setVisible(true);
                    }
                    else{
                        errorMessage.setText("Please enter a valid account");
                        errorMessage.setVisible(true);
                    }
                }

            }
        });
    }

    public ArrayList<String> loan_p(){
       ArrayList<LoanProduct> loan_list = cus.getLoanProduct();
       ArrayList<String> loanlis = new ArrayList<>();
       for(int i =0; i< loan_list.size();i++){
           String str = loan_list.get(i).toString();
           loantype.addItem(str);
           loanlis.add(str);
       }
       return loanlis;
    }

    public boolean check(){
        ArrayList<Loan> own_loans = cus.getLoans();
        if (own_loans!=null) {
            for (int j = 0; j < own_loans.size(); j++) {
                if (loan_name.getName().equals(own_loans.get(j).getLoanName())) {
                    return false;
                }
            }
        }
        return true;
    }
}
