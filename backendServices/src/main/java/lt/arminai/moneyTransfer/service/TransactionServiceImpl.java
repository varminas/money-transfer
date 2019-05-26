package lt.arminai.moneyTransfer.service;

import lombok.NoArgsConstructor;
import lt.arminai.moneyTransfer.dto.exception.DataIntegrationException;
import lt.arminai.moneyTransfer.dto.exception.EntityNotFoundException;
import lt.arminai.moneyTransfer.dto.exception.NotEnoughBalanceException;
import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.model.Transaction;
import lt.arminai.moneyTransfer.model.User;
import lt.arminai.moneyTransfer.persistence.TransactionRepository;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
@Local(value = TransactionService.class)
@NoArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private AccountService accountService;
    private UserService userService;

    @Inject
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public List<Transaction> getTransactionsByAccount(String userId, String accountId) {
        Optional<User> user = userService.getUser(userId);

        if (!user.isPresent()) {
            throw new EntityNotFoundException(String.format("User not found '%s')", userId));
        }

        return user.get().getAccounts().stream()
                .filter(account -> accountId.equals(account.getId()))
                .findFirst()
                .map(account -> transactionRepository.findByAccountNumber(account.getNumber()))
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Transaction transfer(String userId, Transaction transaction) {
        Account accountFrom = getAccount(transaction.getFromAccountNumber(), "FROM");
        Account accountTo = getAccount(transaction.getToAccountNumber(), "TO");

        ensureDataValidity(accountFrom, userId, transaction);

        BigDecimal balance = ensureBalance(accountFrom.getId(), transaction);

        accountService.updateBalance(transaction.getFromAccountNumber(), balance.subtract(transaction.getAmount()));
        accountService.updateBalance(transaction.getToAccountNumber(), accountTo.getBalance().add(transaction.getAmount()));

        return transactionRepository.save(transaction);
    }

    private Account getAccount(String accountNumber, String accountType) {
        return accountService.find(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Account '%s' the money should be transferred %s does not exist",
                                accountNumber, accountType)));
    }

    private void ensureDataValidity(Account accountFrom, String userId, Transaction transaction
    ) {
        if (!accountFrom.getUser().getId().equals(userId)) {
            throw new EntityNotFoundException(String.format("User '%s' and Account '%s' not found", userId, accountFrom));
        }

        if (!accountFrom.getNumber().equals(transaction.getFromAccountNumber())) {
            throw new DataIntegrationException(
                    String.format("Provided accountNumbers '%s' and '%s' are different",
                            accountFrom, transaction.getFromAccountNumber())
            );
        }
    }

    private BigDecimal ensureBalance(String accountId, Transaction transaction) {
        BigDecimal balance = accountService.getBalance(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (transaction.getAmount().compareTo(balance) > 0) {
            throw new NotEnoughBalanceException(String.format("Not enough balance on account '%s'",
                    transaction.getFromAccountNumber())
            );
        }

        return balance;
    }
}
