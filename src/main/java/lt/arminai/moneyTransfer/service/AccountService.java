package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.Account;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface AccountService {
    Optional<Account> getAccount(String userId, String accountId);

    List<Account> find(String userId);
}
