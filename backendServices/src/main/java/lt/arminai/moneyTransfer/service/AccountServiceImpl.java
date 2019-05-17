package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.persistence.AccountRepository;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Local(value = AccountService.class)
@Transactional(Transactional.TxType.SUPPORTS)
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> getAccount(String userId, String accountId) {
        return accountRepository.getByUserIdAndAccountId(userId, accountId);
    }

    @Override
    public Optional<Account> find(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Optional<BigDecimal> getBalance(String accountId) {
        return accountRepository.getById(accountId).map(Account::getBalance);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void updateBalance(String accountNumber, BigDecimal amount) {
        accountRepository.updateBalance(accountNumber, amount);
    }
}
