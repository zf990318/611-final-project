package Account;

import java.time.LocalDateTime;

import LoanStockCurrency.Currency;
import LoanStockCurrency.Loan;
import Transaction.Transaction;
import Transaction.TransactionOP.*;

public class Saving extends Account implements TransferOP, DepositOP, PayLoanOP{

    public Saving(String acc_num_, Float money_, String owner_name_, String owner_id_, String currency_) {
        super(acc_num_, money_, "Saving", owner_name_, owner_id_, currency_);
    }

    @Override
    public Transaction payLoan(Loan loan, Float amount) {
        money -= amount;
        loan.setAmount_due(loan.getAmountDue()-amount);
        String time = LocalDateTime.now().toString();
        return new Transaction(time, "PayLoan", owner_id, owner_name, acc_num, "Saving",amount, currency, "0", "Loan");
    }

    @Override
    public Transaction deposit(Float amount) {
        money += amount;
        String time = LocalDateTime.now().toString();
        return new Transaction(time, "Deposit", owner_id, owner_name, acc_num, "Saving", amount, currency, "0", "Deposit");
    }

    @Override
    public Transaction transfer(Account acc, Float amount) {
        Float target_amount = amount-5;
        if (!currency.equals(acc.currency)){target_amount = amount* Currency.CurrencyRate(currency,acc.currency);
        }
        money -= amount;
        acc.receiveMoney(target_amount);
        String time = LocalDateTime.now().toString();
        return new Transaction(time, "Transfer", owner_id, owner_name, acc_num, "Saving", amount, currency, acc.getAccNum(), acc.getType());
    }
    
}
