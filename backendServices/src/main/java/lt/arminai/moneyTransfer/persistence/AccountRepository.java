package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountRepository {
    Optional<Account> getByUserIdAndAccountId(String userId, String accountId);

    Optional<Account> findByAccountNumber(String userId);

    Optional<Account> getById(String accountId);

    void updateBalance(String accountNumber, BigDecimal amount);
}
