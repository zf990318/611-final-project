package LoanStockCurrency;

public class StockProduct {
    private String stock_name;
    private Float price;

    public StockProduct(String stock_name_, Float price_){
        stock_name = stock_name_;
        price = price_;
    }

    public String getName(){return stock_name;}
    public Float getPrice(){return price;}

    public String toString(){
        String message = "Stock Name: " + stock_name + " & Price: " + price;
        return message;
    }
}
