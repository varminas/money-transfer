package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TransactionRepositoryImpl implements TransactionRepository {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRepositoryImpl.class);

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Override
    public List<Transaction> findByAccountNumber(String accountNumber) {

        return em.createQuery("SELECT t FROM Transaction t WHERE t.fromAccountNumber = :accountNumber", Transaction.class)
                .setParameter("accountNumber", accountNumber)
                .getResultList();
    }

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        logger.info("Persisted transaction {}", transaction);

        em.persist(transaction);

        return transaction;
    }
}
