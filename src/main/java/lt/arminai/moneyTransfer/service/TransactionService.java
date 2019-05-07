package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.Transaction;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TransactionService {
    List<Transaction> getTransactionsByAccount(String accountId);

    Transaction sendMoney(Transaction transaction);
}
