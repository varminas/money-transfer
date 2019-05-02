package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Override
    public Optional<Account> getById(String accountId) {
        return Optional.of(new Account("1", "2", 40));
    }
}
