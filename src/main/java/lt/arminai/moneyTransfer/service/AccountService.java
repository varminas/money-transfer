package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.Account;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.Optional;

@Local
public interface AccountService {
    Optional<Account> getAccount(String userId, String accountId);

    Optional<Account> find(String accountNumber);

    Optional<BigDecimal> getBalance(String accountId);

    void updateBalance(String accountNumber, BigDecimal amount);
}
