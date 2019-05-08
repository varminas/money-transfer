package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.Transaction;
import lt.arminai.moneyTransfer.persistence.TransactionRepository;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Local(value = TransactionService.class)
public class TransactionServiceImpl implements TransactionService {

    @Inject
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Transaction sendMoney(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
