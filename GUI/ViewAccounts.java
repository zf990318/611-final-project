package GUI;

import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAccounts extends JFrame {
    private JButton cancel;
    private JPanel mainPanel;
    private JButton checking;
    private JButton saving;
    private JButton security;
    private ViewAccounts window;
    private CustomerWindow preW;
    private Customer cus;

    public ViewAccounts(CustomerWindow preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.cus=cus;
        this.buttonActions();
        checking.setVisible(false);
        saving.setVisible(false);
        security.setVisible(false);
        this.setButtons();
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

        checking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new CheckingActions(window,cus);
            }
        });
        saving.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new SavingActions(window,cus);
            }
        });
        security.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new SecurityActions(window,cus);
            }
        });


    }

    public void setButtons(){
        if(cus.getCheckingAcc()!= null){
            checking.setVisible(true);
        }
        if(cus.getSavingAcc()!= null){
            saving.setVisible(true);
        }
        if(cus.getSecurityAcc()!=null){
            security.setVisible(true);
        }
    }
}
