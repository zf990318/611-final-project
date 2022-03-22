package LoanStockCurrency;

public class LoanProduct {
    private String loan_name;
    private Float interest;

    public LoanProduct(String loan_name_, Float interest_){
        loan_name = loan_name_;
        interest = interest_;
    }

    public String getName(){return loan_name;}
    public Float getInterest(){return interest;}

    public String toString(){
        String message = "Loan Name: " + loan_name + " & Interest Rate: " + interest;
        return message;
    }
}
