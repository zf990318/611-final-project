package GUI;

import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NewAccountChoice extends JFrame{
    private JPanel mainPanel;
    private JComboBox currency;
    private JTextField Damount;
    private JButton cancel;
    private JButton confirm;
    private JLabel cType;
    private JLabel dAmount;
    private JLabel errorMessage;
    private JFrame f = new JFrame();
    private MakeNewAccount preW;
    private NewAccountChoice window;
    private String aType;
    private Customer cus;
    private String curType;

    public NewAccountChoice(MakeNewAccount preW, String aType, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.preW = preW;
        this.window = this;
        this.bottonActions();
        this.aType = aType;
        this.cus = cus;
        errorMessage.setVisible(false);
        this.currency.addItem("USD");
        this.currency.addItem("CAD");
        this.currency.addItem("CNY");
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
        currency.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) currency.getSelectedItem();
                curType = s;
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Float amount = Float.valueOf(Damount.getText());
                if(amount<5){
                    errorMessage.setText("You have to deposit at least 5 dollar in new account");
                    errorMessage.setVisible(true);
                }

                else{
                    cus.openAccount(aType,curType,amount);
                    errorMessage.setText("Successfully Opened a new " + aType +" account");
                    errorMessage.setVisible(true);
                }
            }
        });
    }
}

