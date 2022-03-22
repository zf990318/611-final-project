package Account;

import java.time.LocalDateTime;
import java.util.*;

import Database.Database;
import LoanStockCurrency.Currency;
import LoanStockCurrency.Stock;
import LoanStockCurrency.StockProduct;
import Transaction.Transaction;
import Transaction.TransactionOP.*;

import javax.xml.crypto.Data;

public class Security extends Account implements TransferOP, BuyStockOP, SellStockOP{
    private ArrayList<Stock> stocks;

    public Security(String acc_num_, Float money_, String owner_name_, String owner_id_, String currency_) {
        super(acc_num_, money_, "Security", owner_name_, owner_id_, currency_);
        stocks = new ArrayList<Stock>();
    }

    public ArrayList<Stock> getStocks(){return stocks;}
    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public Transaction sellStock(Stock stock, Integer amount, Database db) {
        Float earned = amount*stock.getPrice();
        if (!currency.equals("USD")){
            earned = earned * Currency.CurrencyRate("USD",currency);
        }
        money += earned;
        if (amount.equals(stock.getAmount())){
            stock.setAmount(stock.getAmount()-amount);
            stock.sold();
        }
        else{
            stock.setAmount(stock.getAmount()-amount);
        }
        String time = LocalDateTime.now().toString();
        Transaction t = new Transaction(time, "SellStock", owner_id, owner_name, acc_num, "Security", earned, currency,"Stock", stock.getName());
//        t.writeToDB(db);
        return t;
    }

    @Override
    public Transaction buyStock(StockProduct stock_p, Integer amount, Database db) {
        Integer bought = 0;
        Float cost = amount*stock_p.getPrice();
        if (!currency.equals("USD")){
            cost = cost * Currency.CurrencyRate("USD",currency);
        }
        for (Stock stock:stocks){
            if (stock.getName().equals(stock_p.getName())){
                stock.setAmount(amount+stock.getAmount());
                stock.setCost(stock.getCost()+stock_p.getPrice()*amount);
                bought = 1;
                money -= cost;
            }
        }
        if (bought==0){
            Stock stock = db.addStock(stock_p.getName(),cost,amount,0,acc_num);
            money -= cost;
            stocks.add(stock);
        }
        System.out.println(money);
        String time = LocalDateTime.now().toString();
        Transaction t = new Transaction(time, "BuyStock", owner_id, owner_name, acc_num, "Security", stock_p.getPrice()*amount, currency, "Stock", stock_p.getName());
//        t.writeToDB(db);
        return t;
    }

    @Override
    public Transaction transfer(Account acc, Float amount) {
        Float target_amount = amount-5;
        if (!currency.equals(acc.currency)){target_amount = amount* Currency.CurrencyRate(currency,acc.currency);
        }
        money -= amount;
        acc.receiveMoney(target_amount);
        String time = LocalDateTime.now().toString();
        return new Transaction(time, "Transfer", owner_id, owner_name, acc_num, "Security", amount, currency, acc.getAccNum(), acc.getType());
    }

}
