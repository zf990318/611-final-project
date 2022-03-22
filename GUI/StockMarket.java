package GUI;

import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockMarket extends  JFrame{
    private JButton viewsotck;
    private JPanel mainPanel;
    private JButton buystock;
    private JButton sellstock;
    private JButton cancel;
    private StockMarket window;
    private SecurityActions preW;
    private Customer cus;
    private ViewAccounts accounts;

    public StockMarket(ViewAccounts accounts,SecurityActions preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.accounts = accounts;
        this.cus = cus;
        this.buttonActions();
        this.setVisible(true);
        this.setSize(400,400);

    }

    public void buttonActions(){
        viewsotck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new ViewStocks(window,cus);
            }
        });
        buystock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new BuyStocks(accounts,cus);
            }
        });
        sellstock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new SellStocks(accounts,cus);
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


}
