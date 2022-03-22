package GUI;

import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLoans extends JFrame{
    private JButton myloans;
    private JPanel mainPanel;
    private JButton newloans;
    private JButton cancel;
    private ViewLoans window;
    private CustomerWindow preW;
    private Customer cus;

    public ViewLoans(CustomerWindow preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.cus = cus;
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
        myloans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new LoansInfo(window,cus);
            }
        });
        newloans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new RequestLoan(window,cus);
            }
        });
    }
}
