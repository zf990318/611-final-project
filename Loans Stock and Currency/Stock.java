package LoanStockCurrency;

public class Stock {
    private String stock_id;
    private String stock_name;
    private Float price; // current price, in $
    private Float cost;
    private Integer amount;
    private Integer sold; // 1->sold, 0->kept

    public String getID(){return stock_id;}
    public Float getCost(){return cost;}
    public Float getPrice(){return price;}
    public Integer getAmount(){return amount;}
    public String getName(){return stock_name;}
    public Integer getSold(){return sold;}

    public void setCost(Float cost_){cost = cost_;}
    public void setAmount(Integer amount_){amount = amount_;}
    public void sold(){sold=1;}

    public Stock(String stock_id_, String stock_name_, Float price_, Float cost_, Integer amount_, Integer sold_){
        stock_id = stock_id_;
        stock_name = stock_name_;
        price = price_;
        cost = cost_;
        amount = amount_;
        sold = sold_;
    }


    public String toString(){
        String message = "Stock Name: " + stock_name + " & Buy Cost: " + cost + " & Amount: "+amount + " & Price: "+ price;
        return message;
    }
}
