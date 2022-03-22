package GUI;

import LoanStockCurrency.Stock;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewStocks extends JFrame{
    private JList display;
    private JPanel mainPanel;
    private JButton cancel;
    private ViewStocks window;
    private StockMarket preW;
    private Customer cus;

    public ViewStocks(StockMarket preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.cus = cus;
        this.window = this;
        this.preW = preW;
        this.display();
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
    }

    public void display() {
        ArrayList<Stock> stocks = cus.getSecurityAcc().getStocks();
        DefaultListModel model = new DefaultListModel();
        display.setModel(model);
        DefaultListModel listModel = (DefaultListModel) (display.getModel());
        listModel.removeAllElements();
        for (int i = 0; i < stocks.size(); i++) {
            String str = stocks.get(i).toString();
            if(stocks.get(i).getSold()== 0) {
                listModel.addElement(str);
            }
            else{

            }
        }
    }

}
