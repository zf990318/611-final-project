package GUI;

import User.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageLoans extends  JFrame{
    private JButton viewloan;
    private JPanel mainPanel;
    private JButton waitingloans;
    private JButton cancel;
    private ManageLoans window;
    private ManagerWindow preW;
    private Manager mag;
    public ManageLoans(ManagerWindow preW, Manager mag){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.mag=mag;
        this.bottonActions();
        this.setVisible(true);
        this.setSize(400,400);

    }

    public void bottonActions(){
        viewloan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new ApprovedLoans(window,mag);
            }
        });
        waitingloans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                window.setVisible(false);
                new WaitingLoans(window,mag);
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
