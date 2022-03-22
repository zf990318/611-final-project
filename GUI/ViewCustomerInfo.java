package GUI;

import Transaction.Transaction;
import User.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewCustomerInfo extends  JFrame{
    private JList display;
    private JPanel mainPanel;
    private JButton cancel;
    private ViewCustomerInfo window;
    private ManagerWindow preW;
    private Manager mag;

    public ViewCustomerInfo(ManagerWindow preW,Manager mag){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.mag = mag;
        this.bottonActions();
        this.setVisible(true);
        this.mag = mag;
        this.display();
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
    }

    public void display() {
        ArrayList<String> cus_info = mag.getCustomerInfo();
        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        for (int i = 0; i < cus_info.size(); i++) {
            String str = cus_info.get(i);
            listModel.addElement(str);
        }
    }
}
