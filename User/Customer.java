package User;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Account.*;
import Database.Database;
import LoanStockCurrency.*;
import Transaction.Transaction;
import Transaction.TransactionOP.*;

public class Customer extends User implements RequestLoanOP{

    // Three Accounts
    private Checking checking;
    private Saving saving;
    private Security security;

    // Loan & New Transactions & old Transactions
    private ArrayList<Loan> loans;
    private Database db;

    public Customer(String id_, String name_, String user_name_, String password_){
        super(id_, name_, password_, user_name_);
        loans = null;
    }

    // Accessor
    public String getName(){return user_name;}
    public Checking getCheckingAcc(){return checking;}
    public Saving getSavingAcc(){return saving;}
    public Security getSecurityAcc(){return security;}
    public ArrayList<Loan> getLoans(){return loans;}
    public Database getDB(){return db;}
    
    // Personal Info Edition
    public void setChecking(Checking checking_){checking = checking_;}
    public void setSaving(Saving saving_){saving = saving_;}
    public void setSecurity(Security security_){security = security_;}
    public void setLoans(ArrayList<Loan> loans_){loans = loans_;}
    public void setDB(Database db_){db = db_;}

    // Open Account
    public void openAccount(String type, String currency, Float amount){
        switch (type) {
            case "Checking":
                // db open account -> pass Float money_, String owner_name_, String owner_id_, String currency_ -> get Checking obj
                checking = (Checking) db.addAccount(amount-5, "Checking", user_name, id, currency);
                db.updateManager(5f);
                break;
            case "Saving":
                saving = (Saving) db.addAccount(amount-5, "Saving", user_name, id, currency);
                db.updateManager(5f);
                break;
            case "Security":
                security = (Security) db.addAccount(amount-5, "Security", user_name, id, currency);
                db.updateManager(5f);
                break;
        }
    }

    // Transfer Money
    public void transfer(Account source_acc, Float amount, Account target_acc){
        Transaction t = ((TransferOP) source_acc).transfer(target_acc, amount);
        t.writeToDB(db);
        db.updateManager(5f);
        DBUpdate();
    }

    // Deposit Money
    public void deposit(Account target_acc, Float amount){
        Transaction t = ((DepositOP) target_acc).deposit(amount-5);
        t.writeToDB(db);
        db.updateManager(5f);
        DBUpdate();
    }

    // Withdraw Money
    public void withdraw(Account acc, Float amount){
        Transaction t = ((WithdrawOP) acc).withdraw(amount);
        t.writeToDB(db);
        db.updateManager(5f);
        DBUpdate();
    }

    // Request Loan
    @Override
    public void requestLoan(LoanProduct loan_p, Float amount, Account acc) {
        // not implemented yet
        String time = LocalDateTime.now().toString();
        Loan loan = db.addLoan(loan_p.getName(),loan_p.getInterest(),acc.getCurrency(),id,user_name,acc.getAccNum(),amount*(1+loan_p.getInterest()),0,0);
        if (loans==null){loans = new ArrayList<Loan>();}
        loans.add(loan);
        System.out.println(loan);
        Transaction t = new Transaction(time, "RequestLoan", id, name, acc.getAccNum(), acc.getType(),
                amount, acc.getCurrency(), "0", loan_p.getName());
        t.writeToDB(db);
        DBUpdate();
    }

    // Pay Loan
    public void payLoan(Account acc, Loan loan, Float amount){
        Transaction t = ((PayLoanOP) acc).payLoan(loan, amount);
        t.writeToDB(db);
        DBUpdate();
    }

    // Buy Stock
    public void buyStock(StockProduct stock_p, Integer amount){
        Transaction t = security.buyStock(stock_p, amount, db);
        t.writeToDB(db);
        DBUpdate();
    }

    // Sell Stock
    public void sellStock(Stock stock, Integer amount){
        Transaction t = security.sellStock(stock, amount, db);
        t.writeToDB(db);
        DBUpdate();
    }

    // delete account
    public void deleteAccount(String type){
        switch (type) {
            case "Checking":
                db.deleteAccount(checking.getAccNum());
                checking = null;
                break;
            case "Saving":
                db.deleteAccount(saving.getAccNum());
                saving = null;
                break;
            case "Security":
                db.deleteAccount(security.getAccNum());
                security = null;
                break;
        }
        db.updateManager(5f);
    }

    // View Transactions
    public ArrayList<String> viewAllTransactions(){
        // get old transactions from database
        ArrayList<Transaction> transactions = db.getTransaction(id);
        ArrayList<String> message = new ArrayList<String>();
        for (Transaction t: transactions){
            message.add(t.toString());
        }
        return message;
    }

    // View Loans
    public ArrayList<String> viewLoans(){
        ArrayList<String> message = new ArrayList<String>();
        for (Loan loan: loans){
            message.add(loan.toString());
        }
        return message;
    }

    public ArrayList<LoanProduct> getLoanProduct(){
        ArrayList<LoanProduct> loans_p = db.getProductsInfo();
        return loans_p;
    }

    public ArrayList<StockProduct> getStockProduct(){
        ArrayList<StockProduct> stock_p = db.getMarketInfo();
        return stock_p;
    }

    // View Stocks
    public ArrayList<String> viewStocks(){
        ArrayList<Stock> stocks = security.getStocks();
        ArrayList<String> message = new ArrayList<String>();
        for (Stock stock: stocks){
            message.add(stock.toString());
        }
        return message;
    }

    //
    public void DBUpdate(){
        // update checking/saving/security
        if (checking != null){db.updateBalance(checking.getAccNum(), checking.getMoney());}
        if (saving != null){db.updateBalance(saving.getAccNum(), saving.getMoney());}
        if (security != null){
            db.updateBalance(security.getAccNum(), security.getMoney());
            for (Stock stock:security.getStocks()){
                db.updateUserStock(stock.getID(),stock.getCost(),stock.getAmount(),stock.getSold());
            }
        }
        if (loans!=null){
            for (int i=0;i<loans.size();i++){
                Loan loan = loans.get(i);
                db.updateLoan(loan.getLoanID(),loan.getAmountDue(),loan.getFinished(),loan.getConfirmed());
            }
        }

    }
}
