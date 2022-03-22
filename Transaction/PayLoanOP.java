package Transaction.TransactionOP;

import LoanStockCurrency.Loan;
import Transaction.Transaction;

public interface PayLoanOP {
    Transaction payLoan(Loan loan, Float amount);
}
