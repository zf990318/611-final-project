package LoanStockCurrency;

public class Loan {
    // Loan Product Info
    private String loan_id;
    private String loan_name;
    private Float interest; // interest rate
    private String currency;
    private Float amount_due; // amount need to pay
    private String acc_num;

    // flags
    private Integer finished;
    private Integer confirmed;

    public Loan(String loan_id_, String loan_name_, Float interest_, String currency_,
                Float amount_due_, Integer finished_, Integer confirmed_, String acc_num_){
        loan_id = loan_id_;
        loan_name = loan_name_;
        interest = interest_;
        currency = currency_;
        amount_due = amount_due_;
        finished = finished_;
        confirmed = confirmed_;
        acc_num = acc_num_;
    }

    public Float getAmountDue(){return amount_due;}
    public String getCurrency(){return currency;}
    public String getLoanID(){return loan_id;}
    public Integer getFinished(){return finished;}
    public Integer getConfirmed(){return confirmed;}
    public String getLoanName(){return loan_name;}
    public String getAccID(){return acc_num;}
    public Float getInterest(){return interest;}

    public void confirm(){
        confirmed = 1;
    }
    public void setFinished(){finished = 1;}
    public void setAmount_due(Float amount_due_){
        amount_due = amount_due_;
        if (amount_due==0){setFinished();}
    }

    public String toString(){
        String message =  "Loan iD: "+ loan_id+" & Account Number: " +acc_num+"Loan Name: " + loan_name + " & Currency: "+ currency+" & Interest: "+ interest+" & Amount Due:"+ amount_due;
        return message;
    }
}
