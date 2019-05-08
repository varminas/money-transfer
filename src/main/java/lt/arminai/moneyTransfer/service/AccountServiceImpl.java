package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.persistence.AccountRepository;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Local(value = AccountService.class)
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> getAccount(String userId, String accountId) {
        return accountRepository.getByUserIdAndAccountId(userId, accountId);
    }

    @Override
    public List<Account> find(String userId) {
        return accountRepository.findByUserId(userId);
    }
}
