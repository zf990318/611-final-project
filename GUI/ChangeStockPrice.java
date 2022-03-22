package GUI;

import LoanStockCurrency.StockProduct;
import User.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChangeStockPrice extends  JFrame{
    private JPanel mainPanel;
    private JList display;
    private JTextField scode;
    private JLabel stockcode;
    private JLabel newprice;
    private JTextField NewPrice;
    private JLabel errorMessage;
    private JButton cancel;
    private JButton confirm;
    private ChangeStockPrice window;
    private ManagerWindow preW;
    private Manager mag;

    public ChangeStockPrice(ManagerWindow preW, Manager mag){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.mag = mag;
        this.preW = preW;
        this.display();
        this.bottonActions();
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
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stock = scode.getText();
                Float new_price = Float.valueOf(NewPrice.getText());
                if(stock.equalsIgnoreCase("stock1")||stock.equalsIgnoreCase("stock2")||stock.equalsIgnoreCase("fb")){
                    mag.setStockPrice(stock,new_price);
                    errorMessage.setText("New Price Set Successfully");
                    errorMessage.setVisible(true);
                }
                else{
                    errorMessage.setText("The stock code does not exits, please enter again");
                    errorMessage.setVisible(true);
                }
            }
        });
    }

    public void display() {
        ArrayList<StockProduct> s_ps = mag.getStockProduct();
        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        for (int i = 0; i < s_ps.size(); i++) {
            String str = s_ps.get(i).toString();
            listModel.addElement(str);
        }
    }
}
