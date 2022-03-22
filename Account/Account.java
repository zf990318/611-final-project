package Account;
public abstract class Account  {
    protected String acc_num;
    protected Float money;
    protected String type;
    protected String owner_name;
    protected String owner_id;
    protected String currency;

    Account(String acc_num_, Float money_, String type_, String owner_name_, String owner_id_, String currency_){
        acc_num = acc_num_;
        money = money_;
        type = type_;
        owner_name = owner_name_;
        owner_id = owner_id_;
        currency = currency_;
    }

    // Accessor
    public String getAccNum(){return acc_num;}
    public Float getMoney(){return money;}
    public String getType(){return type;}
    public String getCurrency(){return currency;}
    public String getUserName(){return owner_name;}

    // increase money
    public void receiveMoney(Float amount){ money += amount;}
}