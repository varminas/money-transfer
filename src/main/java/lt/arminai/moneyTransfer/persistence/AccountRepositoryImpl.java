package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {
    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Override
    public Optional<Account> getById(int accountId) {
        Account account = em.find(Account.class, accountId);
        logger.info("Account from DB {}", account);
//        return Optional.ofNullable(account);
        return Optional.of(new Account(1, "2", 40));
    }
}
