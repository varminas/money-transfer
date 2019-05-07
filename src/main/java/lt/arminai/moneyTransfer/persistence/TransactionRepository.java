package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findByAccount(String accountId);

    Transaction save(Transaction transaction);
}
