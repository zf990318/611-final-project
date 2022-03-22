package User;

import java.util.ArrayList;

import Database.Database;
import LoanStockCurrency.*;
import Transaction.Transaction;

public class Manager extends User{
    private Float profit;

    private ArrayList<Loan> loan_products;
    private ArrayList<Stock> stock_products;
    private Database db;


    public Manager(String id_, String name_, String password_, String user_name_, Float profit_){
        super(id_, name_, password_, user_name_);
        profit = profit_;
    }
    public Database getDB(){return db;}
    public void setDB(Database db_){db=db_;}
    public Float getProfit(){return profit;}

    // get all stock products
    public ArrayList<StockProduct> getStockProduct(){
        ArrayList<StockProduct> stock_p = db.getMarketInfo();
        return stock_p;
    }

    // change stock price
    public void setStockPrice(String name, Float price_){
         db.updateMarket(name, price_);
    }
    
    // view transaction
    public ArrayList<Transaction> viewTransactions(){
        return db.getAllTransaction();
    }

    public ArrayList<String> getCustomerInfo(){
        return db.getAllCustomers();
    }

    public ArrayList<Transaction> viewTransactions(String user_id){
        return db.getTransaction(user_id);
    }

    // view Unconfirmed Loan
    public ArrayList<Loan> getApprovedLoan(){return db.managerGetConfirmedLoans();}

    public ArrayList<Loan> getUnapprovedLoan(){
        return db.managerGetUnconfirmedLoans();
    }

    // confirm requested Loan
    public void confirmLoan(Loan loan){
        loan.confirm();
        db.updateLoan(loan.getLoanID(),loan.getAmountDue(), loan.getFinished(),loan.getConfirmed());
        db.addBalance(loan.getAccID(), loan.getAmountDue()/(1+loan.getInterest()));
        System.out.println(loan.getAmountDue()/(1+loan.getInterest()));
//        System.out.println(loan.getAmountDue()*loan.getInterest()/(1+loan.getInterest()));
        db.updateManager(loan.getAmountDue()*loan.getInterest()/(1+loan.getInterest()));
        profit += loan.getAmountDue()*loan.getInterest()/(1+loan.getInterest());
    }


    // // Manage loan pruducts (Delete/Adjust Interest/Add)
    // public void addLoan(){}
    // public void deleteLoan(){}
    // public void setLoanInterest(Loan loan, Float interest){
    //     loan.setInterest(interest);
    // }
}
