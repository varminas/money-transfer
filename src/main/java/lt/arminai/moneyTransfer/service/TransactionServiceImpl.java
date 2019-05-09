package lt.arminai.moneyTransfer.service;

import lombok.NoArgsConstructor;
import lt.arminai.moneyTransfer.dto.exception.DataIntegrationException;
import lt.arminai.moneyTransfer.dto.exception.EntityNotFoundException;
import lt.arminai.moneyTransfer.dto.exception.NotEnoughBalanceException;
import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.model.Transaction;
import lt.arminai.moneyTransfer.persistence.AccountRepository;
import lt.arminai.moneyTransfer.persistence.TransactionRepository;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
@Local(value = TransactionService.class)
@NoArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private AccountService accountService;

    @Inject
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED )
    public Transaction transfer(String userId, String accountId, Transaction transaction) {
        Optional<Account> accountFrom = accountService.find(transaction.getFromAccountNumber());
        Optional<Account> accountTo = accountService.find(transaction.getToAccountNumber());

        ensureDataValidity(accountFrom, accountTo, userId, accountId, transaction);

        BigDecimal balance = ensureBalance(accountId, transaction);

        accountService.updateBalance(transaction.getFromAccountNumber(), balance.subtract(transaction.getAmount()));
        accountService.updateBalance(transaction.getToAccountNumber(), balance.add(transaction.getAmount()));

        return transactionRepository.save(transaction);
    }

    private void ensureDataValidity(Optional<Account> accountFrom, Optional<Account> accountTo, String userId,
                                    String accountId, Transaction transaction
    ) {
        Account account = accountFrom
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Account '%s' the money should be transferred from does not exist",
                                transaction.getFromAccountNumber())));

        if (!account.getUser().getId().equals(userId)) {
            throw new EntityNotFoundException(String.format("User '%s' and Account '%s' not found", userId, accountId));
        }

        if (!accountTo.isPresent()) {
            throw new EntityNotFoundException(
                    String.format("Account '%s' the money should be transferred to does not exist",
                            transaction.getToAccountNumber())
            );
        }

        if (!account.getNumber().equals(transaction.getFromAccountNumber())) {
            throw new DataIntegrationException(
                    String.format("Provided accountId '%s' and accountNumber '%s' belongs to different account",
                            accountId, transaction.getFromAccountNumber())
            );
        }
    }

    private BigDecimal ensureBalance(String accountId, Transaction transaction) {
        BigDecimal balance = accountService.getBalance(accountId).orElse(BigDecimal.ZERO);

        if (transaction.getAmount().compareTo(balance) > 0) {
            throw new NotEnoughBalanceException(String.format("Not enough balance on account '%s'",
                    transaction.getFromAccountNumber())
            );
        }

        return balance;
    }
}
