package Transaction.TransactionOP;

import Database.Database;
import LoanStockCurrency.Stock;
import Transaction.Transaction;

public interface SellStockOP {
    Transaction sellStock(Stock stock, Integer amount, Database db);
}
