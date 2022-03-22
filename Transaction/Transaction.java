package Transaction;

import java.time.LocalDateTime;

import Database.Database;

public class Transaction {
    // Transaction Format: time/type/user_id/user_name/acc_num/acc_type/amount/currency/target_id/target_type

    // Time & Type Info
    private String time;
    private String type;

    // Source Info
    private String user_id;
    private String user_name;
    private String acc_num;
    private String acc_type;

    // Money Infor
    private Float amount;
    private String currency;

    // Target Info
    private String target_acc_num; // 0/acc_num/stock_id/loan_id
    private String target_type; // Manager/Checking or Saving or Security/Stock/Loan/Deposit/Withdraw

    public Transaction(String time_, String type_, String user_id_, String user_name_, String acc_num_, String acc_type_,
        Float amount_, String currency_, String target_acc_num_, String target_type_){  
        time = time_;
        type = type_;
        user_id = user_id_;
        user_name = user_name_;
        acc_num = acc_num_;
        acc_type = acc_type_;
        amount = amount_;
        currency = currency_;
        target_acc_num = target_acc_num_;
        target_type = target_type_;
    }

    @Override
    public String toString() {
        String message = "";
        time = time.substring(0,19);
//        time = temp[0];
        switch (type){
            case "Withdraw": case "Deposit":
                message = "Time: "+time+" & "+type+" & User Name: " + user_name + " & Account: " + acc_type + " & Currency: "+currency+" & Amount: "+ amount;
                break;
            case "Transfer":
                message = "Time: "+time+" & "+type+" & User Name: " + user_name + " & From: " + acc_type + " & To: "+ target_type+" & Currency: "+currency+" & Amount: "+ amount;
                break;
            case "BuyStock": case "SellStock":
                message = "Time: "+time+" & "+type+" & User Name: " + user_name + " & Account: " + acc_type + " & Currency: "+currency +" & Amount: "+ amount + " & Stock Name: "+target_type;
                break;
            case "RequestLoan": case "PayLoan":
                message = "Time: "+time+" & "+type+" & User Name: " + user_name + " & Account: " + acc_type + " & Currency: "+currency +" & Amount: "+ amount+ " & Loan Name: "+target_type;
        }
//        message += time + "/" + type + "/" + user_id + "/" + user_name + "/" + acc_num + "/" + acc_type + "/" +
//            amount + "/" + currency + "/" + target_acc_num + "/" + target_type;
        return message;
    }

    public void writeToDB(Database db){
        db.addTransaction(time, type, user_id, user_name, acc_num, acc_type, amount, currency, target_acc_num, target_type);
    }

    public static void main(String[] args) {
        String time = LocalDateTime.now().toString();
        System.out.println(time);
    }
}
