package GUI;

import LoanStockCurrency.Currency;
import LoanStockCurrency.StockProduct;
import User.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BuyStocks extends JFrame{
    private JTextField stockamount;
    private JLabel sproduct;
    private JLabel amount;
    private JButton cancel;
    private JButton confirm;
    private JPanel mainPanel;
    private BuyStocks window;
    private ViewAccounts preW;
    private JLabel errorMessage;
    private JComboBox StockProduct;
    private Customer cus;
    private String sProduct;
    private ArrayList<StockProduct> productList;
    private String stock;
    private StockProduct s_p;

    public BuyStocks(ViewAccounts preW, Customer cus){
        this.setContentPane(mainPanel);
        this.pack();
        this.cus =cus;
        this.window = this;
        errorMessage.setVisible(false);
        this.preW = preW;
        this.productList = this.products();
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
                String s = (String) StockProduct.getSelectedItem();
                stock =s;
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer amount = Integer.valueOf(stockamount.getText());
                for(int i =0; i< productList.size();i++){
                    if(stock.equals(productList.get(i).toString())){
                        s_p = productList.get(i);
                    }
                }
                String curType = cus.getSecurityAcc().getCurrency();
                if(!(curType.equals("USD"))){
                    Float rate = Currency.CurrencyRate("USD",curType);
                    Float price = amount*rate*s_p.getPrice();
                    if(cus.getSecurityAcc().getMoney()<price){
                        errorMessage.setText("You do not have enough money");
                        errorMessage.setVisible(true);
                    }
                    else{
//                        System.out.println("running");
                        cus.buyStock(s_p,amount);
                        errorMessage.setText("Stock been added");
                        errorMessage.setVisible(true);

                    }
                }
                else{
                    Float price = amount*s_p.getPrice();
                    if(cus.getSecurityAcc().getMoney()<price){
                        errorMessage.setText("You do not have enough money");
                        errorMessage.setVisible(true);
                    }
                    else{
                        cus.buyStock(s_p,amount);
                        errorMessage.setText("Stock been added");
                        errorMessage.setVisible(true);
                    }

                }
            }
        });
    }

    public ArrayList<StockProduct> products(){
        ArrayList<StockProduct> product = cus.getStockProduct();
        for(int i =0; i< product.size();i++){
            String str = product.get(i).toString();
            StockProduct.addItem(str);
        }
        return product;
    }
}
