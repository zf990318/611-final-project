package GUI;

import LoanStockCurrency.Currency;
import LoanStockCurrency.Stock;
import LoanStockCurrency.StockProduct;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SellStocks extends JFrame{
    private JList display;
    private JTextField stockcode;
    private JTextField stockamount;
    private JPanel mainPanel;
    private JLabel product;
    private JLabel amount;
    private JButton cancel;
    private JButton confirm;
    private JLabel errorMessage;
    private JComboBox StockProduct;
    private SellStocks window;
    private ViewAccounts preW;
    private Customer cus;
    private Stock s_p;
    private Integer index;
    private ArrayList<Stock> stockList;

    public SellStocks(ViewAccounts preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.window = this;
        this.preW = preW;
        this.cus = cus;
        this.stockList = this.products();
        errorMessage.setVisible(false);
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
                new SecurityActions(preW,cus);
            }
        });
        StockProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=StockProduct.getSelectedIndex();
                index = i;
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer amount = Integer.valueOf(stockamount.getText());
                s_p = stockList.get(index);
                String curType = cus.getSecurityAcc().getCurrency();
                if(!(curType.equals("USD"))){
                    Float rate = Currency.CurrencyRate("USD",curType);
                    Float price = amount*rate*s_p.getPrice();
                    //换算汇率
                    if(s_p.getAmount()< amount){
                        errorMessage.setText("You do not have enough stocks");
                        errorMessage.setVisible(true);
                    }
                    else{
                        cus.getSecurityAcc().sellStock(s_p,amount, cus.getDB());
                        errorMessage.setText("Stock been sold");
                        errorMessage.setVisible(true);

                    }
                }
                else{
                    Float price = amount*s_p.getPrice();
                    if(s_p.getAmount()<amount){
                        errorMessage.setText("You do not have enough stocks");
                        errorMessage.setVisible(true);
                    }
                    else{
                        cus.sellStock(s_p,amount);
                        errorMessage.setText("Stock been sold");
                        errorMessage.setVisible(true);
                    }

                }
            }

        });
    }

    public ArrayList<Stock> products(){
        ArrayList<Stock> product = cus.getSecurityAcc().getStocks();
        for(int i =0; i< product.size();i++){
            String str = product.get(i).toString();
            if(product.get(i).getSold() == 0) {
                StockProduct.addItem(str);
            }
            else{

            }
        }
        return product;
    }
}
