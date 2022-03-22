package Transaction.TransactionOP;

import Database.Database;
import Transaction.Transaction;

import java.util.ArrayList;

import LoanStockCurrency.*;

public interface BuyStockOP {
    Transaction buyStock(StockProduct stock_p, Integer amount, Database db);
}
