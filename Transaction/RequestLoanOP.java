package Transaction.TransactionOP;
import java.util.ArrayList;

import Account.Account;
import LoanStockCurrency.Loan;
import LoanStockCurrency.LoanProduct;

public interface RequestLoanOP {
    void requestLoan(LoanProduct loan_p, Float amount, Account acc);
}
