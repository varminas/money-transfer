package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> getById(String accountId);
}
