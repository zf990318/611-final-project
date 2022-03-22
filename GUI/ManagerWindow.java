package GUI;

import User.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerWindow extends JFrame{
    private JPanel mainPanel;
    private JButton customerinfo;
    private JButton loans;
    private JButton transactions;
    private JButton stock;
    private JLabel welcome;
    private JButton logout;
    private JButton profit;
    private LoginWindow preW;
    private ManagerWindow window;
    private Manager mag;

    public ManagerWindow(LoginWindow preW, Manager mag){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.mag = mag;
        this.bottonActions();
        this.setVisible(true);
        this.setSize(400,400);


    }

    public void bottonActions(){
        customerinfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new ViewCustomerInfo(window, mag);
            }
        });
        loans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new ManageLoans(window,mag);
            }
        });

        stock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new ChangeStockPrice(window,mag);
            }
        });
        transactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new CustomerTransactions(window,mag);
            }
        });

        profit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new Profit(window,mag);
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                preW.setVisible(true);
            }
        });
    }
}
