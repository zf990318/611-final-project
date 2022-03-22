package Account;

import java.time.LocalDateTime;

import LoanStockCurrency.Currency;
import LoanStockCurrency.Loan;
import Transaction.Transaction;
import Transaction.TransactionOP.*;

public class Checking extends Account implements DepositOP, PayLoanOP, TransferOP, WithdrawOP{

    public Checking(String acc_num_, Float money_, String owner_name_, String owner_id_, String currency_) {
        super(acc_num_, money_, "Checking", owner_name_, owner_id_, currency_);
    }

    // Transaction(String time_, String type_, Integer user_id_, String user_name_, Integer acc_num_, String acc_type_,
    // Integer amount_, String currency_, Integer target_id_, String target_type_)
    @Override
    public Transaction deposit(Float amount) {
        money += amount;
        String time = LocalDateTime.now().toString();
        return new Transaction(time, "Deposit", owner_id, owner_name, acc_num, "Checking", amount, currency, "0", "Deposit");
    }

    @Override
    public Transaction payLoan(Loan loan, Float amount) {
        money -= amount;
        loan.setAmount_due(loan.getAmountDue()-amount);
        String time = LocalDateTime.now().toString();
        return new Transaction(time, "PayLoan", owner_id, owner_name, acc_num, "Checking", amount, currency, "0","Loan");
    }

    @Override
    public Transaction transfer(Account acc, Float amount) {
        Float target_amount = amount-5;
        if (!currency.equals(acc.currency)){target_amount = amount*Currency.CurrencyRate(currency,acc.currency);
        }
        money -= amount;
        acc.receiveMoney(target_amount);
        String time = LocalDateTime.now().toString();
        return new Transaction(time, "Transfer", owner_id, owner_name, acc_num, "Checking", amount, currency, acc.getAccNum(), acc.getType());
    }

    @Override
    public Transaction withdraw(Float amount) {
        money -= amount;
        String time = LocalDateTime.now().toString();
        return new Transaction(time, "Withdraw", owner_id, owner_name, acc_num, "Checking", amount, currency, "0", "Withdraw");
    }
    
}
