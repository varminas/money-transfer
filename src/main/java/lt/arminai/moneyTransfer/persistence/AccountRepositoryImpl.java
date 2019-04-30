package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Account;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Optional<Account> getById(String accountId) {
        return Optional.of(new Account("1", "2", 40));
    }
}
