package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.model.Account;
import org.junit.Test;

import static lt.arminai.moneyTransfer.converter.TestData.getAccount;
import static lt.arminai.moneyTransfer.converter.TestData.getAccountDto;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AccountConverterTest {
    @Test
    public void null_toDto_null() {
        assertNull(AccountConverter.toDto(null));
    }

    @Test
    public void fullAccountObject_toDto_correctAccountObject() {
        Account account = getAccount();

        AccountDto accountDto = getAccountDto();

        assertThat(AccountConverter.toDto(account), is(accountDto));
    }

    @Test
    public void null_fromDto_null() {
        assertNull(UserConverter.fromDto(null));
    }

    @Test
    public void fullAccountObject_fromDto_correctAccountObject() {
        AccountDto accountDto = getAccountDto();

        Account account = getAccount();

        assertThat(AccountConverter.fromDto(accountDto), is(account));
    }
}
