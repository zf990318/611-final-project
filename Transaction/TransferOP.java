package Transaction.TransactionOP;

import Account.*;
import Transaction.Transaction;

public interface TransferOP {
    Transaction transfer(Account acc, Float amount);
}
