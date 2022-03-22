package GUI;

import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecurityActions extends JFrame{
    private JButton transfer;
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton stockmarket;
    private JList display;
    private SecurityActions window;
    private ViewAccounts preW;
    private Customer cus;

    public SecurityActions(ViewAccounts preW,Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.cus = cus;
        this.buttonActions();
        this.display();
        this.setVisible(true);
        this.setSize(400,400);



    }

    public void buttonActions(){
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                preW.setVisible(true);
            }
        });

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transfer(cus, cus.getSecurityAcc(),preW);
            }
        });

        stockmarket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new StockMarket(preW,window,cus);
            }
        });
    }

    public void display(){
        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        String balance = String.valueOf(cus.getSecurityAcc().getMoney());
        String bMessage = "Account Balance: " + balance;
        listModel.addElement(bMessage);
        String curType = cus.getSecurityAcc().getCurrency();
        String cMessage = "Currency Type: " + curType;
        listModel.addElement(cMessage);
    }


}
