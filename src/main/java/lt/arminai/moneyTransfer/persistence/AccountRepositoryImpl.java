package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {
    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Override
    public Optional<Account> getByUserIdAndAccountId(String userId, String accountId) {
        String SQL = "SELECT a FROM Account a WHERE t.oid = :accountId AND t.userId = :userId";

        return Optional.ofNullable(em.createQuery(SQL, Account.class)
                .setParameter("accountId", accountId)
                .setParameter("userId", userId)
                .getSingleResult()
        );
    }

    @Override
    public List<Account> findByUserId(String userId) {
        String SQL = "SELECT a FROM Account a WHERE t.userId = :userId";

        return em.createQuery(SQL, Account.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
