package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.Account;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface AccountService {
    Optional<Account> getAccount(long accountId);
}
