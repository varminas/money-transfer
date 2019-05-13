package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Optional;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {
    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Override
    public Optional<Account> getByUserIdAndAccountId(String userId, String accountId) {
        String SQL = "SELECT a FROM Account a WHERE a.id = :accountId AND a.user.id = :userId";

        try {
           return Optional.of(em.createQuery(SQL, Account.class)
                    .setParameter("accountId", accountId)
                    .setParameter("userId", userId)
                    .getSingleResult()
           );
        } catch (NoResultException e) {
            logger.warn("No data found for userId {} and accountId {}", userId, accountId);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        String SQL = "SELECT a FROM Account a WHERE a.number = :number";

        return em.createQuery(SQL, Account.class)
                .setParameter("number", accountNumber)
                .getResultList().stream().findFirst();
    }

    @Override
    public Optional<Account> getById(String accountId) {
        return Optional.ofNullable(em.find(Account.class, accountId));
    }

    @Override
    public void updateBalance(String accountNumber, BigDecimal amount) {
        findByAccountNumber(accountNumber)
                .ifPresent(account -> {
                    account.setBalance(amount);
                    em.persist(account);
                });
    }
}
