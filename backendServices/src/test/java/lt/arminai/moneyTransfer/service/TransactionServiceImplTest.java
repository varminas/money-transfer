package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.model.Transaction;
import lt.arminai.moneyTransfer.model.User;
import lt.arminai.moneyTransfer.persistence.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {
    TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepositoryMock;

    @Mock
    AccountService accountServiceMock;

    @Mock
    UserService userServiceMock;

    @Captor
    ArgumentCaptor<Transaction> transactionArgumentCaptor;

    @Before
    public void setup() {
        transactionService = new TransactionServiceImpl(transactionRepositoryMock, accountServiceMock, userServiceMock);
    }

    @Test
    public void transactionsListIsEmpty() {
        String accountNr = "58fdb294-92f1-469c-80fd-270a1e9596df";
        String userId = "123";
        Account account = Account.builder()
                .id(accountNr)
                .number(accountNr)
                .build();

        List<Account> accounts = Arrays.asList(account);
        User user = User.builder().id("1")
                .accounts(accounts)
                .build();
        Transaction transaction = Transaction.builder()
                .amount(BigDecimal.ONE)
                .fromAccountNumber(accountNr)
                .toAccountNumber("456")
                .build();

        when(userServiceMock.getUser(userId)).thenReturn(Optional.of(user));
        when(transactionRepositoryMock.findByAccountNumber(accountNr)).thenReturn(Arrays.asList(transaction));

        List<Transaction> transactions = transactionService.getTransactionsByAccount(userId, accountNr);

        assertThat(transactions.size(), is(1));
        verify(userServiceMock, times(1)).getUser(userId);
        verify(transactionRepositoryMock, times(1)).findByAccountNumber(accountNr);
    }

    @Test
    public void transfer() {
        String userId = "123456789";
        String accountFromNr = "58fdb294-92f1-469c-80fd-270a1e9596df";
        String accountToNr = "a8fdb294-92f1-469c-80fd-270a1e9596d1";
        User user = User.builder()
                .id(userId)
                .username("12356788")
                .firstName("Fname")
                .lastName("Lname")
                .build();

        Account accountFrom = Account.builder()
                .id(accountFromNr)
                .number(accountFromNr)
                .balance(BigDecimal.valueOf(5000))
                .build();
        accountFrom.setUser(user);

        Account accountTo = Account.builder()
                .id(accountToNr)
                .number(accountToNr)
                .balance(BigDecimal.valueOf(4000))
                .build();
        accountTo.setUser(user);

        Transaction transaction = Transaction.builder()
                .fromAccountNumber(accountFromNr)
                .toAccountNumber(accountToNr)
                .amount(BigDecimal.TEN)
                .build();

        when(accountServiceMock.find(accountFromNr)).thenReturn(Optional.of(accountFrom));
        when(accountServiceMock.find(accountToNr)).thenReturn(Optional.of(accountTo));
        when(accountServiceMock.getBalance(accountFromNr)).thenReturn(Optional.of(BigDecimal.valueOf(5000)));
        when(transactionRepositoryMock.save(transaction)).thenReturn(transaction);

        Transaction transfer = transactionService.transfer(userId, transaction);

        assertNotNull(transfer);
        verify(accountServiceMock, times(1)).find(accountFromNr);
        verify(accountServiceMock, times(1)).find(accountToNr);
        verify(accountServiceMock, times(1)).updateBalance(accountFromNr, BigDecimal.valueOf(4990));
        verify(accountServiceMock, times(1)).updateBalance(accountToNr, BigDecimal.valueOf(4010));
        verify(transactionRepositoryMock, times(1)).save(transactionArgumentCaptor.capture());
        assertNotNull(transactionArgumentCaptor.getValue());
    }
}
