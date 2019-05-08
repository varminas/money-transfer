package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Optional<Account> getByUserIdAndAccountId(String userId, String accountId);

    List<Account> findByUserId(String userId);
}
