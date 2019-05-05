package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.dto.CurrencyDto;
import lt.arminai.moneyTransfer.dto.UserDto;
import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.model.Currency;
import lt.arminai.moneyTransfer.model.User;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static lt.arminai.moneyTransfer.converter.TestData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class UserConverterTest {
    @Test
    public void null_toDto_null() {
        assertNull(UserConverter.toDto(null));
    }

    @Test
    public void fullUserObject_toDto_correctUserObject() {
        Account account = getAccount();
        User user = getUser(Arrays.asList(account));

        AccountDto accountDto = getAccountDto();
        UserDto userDto = getUserDto(Arrays.asList(accountDto));

        assertThat(UserConverter.toDto(user), is(userDto));
    }

    @Test
    public void userObject_with_empty_Accounts_list_toDto_correctUserObject() {
        User user = getUser(emptyList());

        UserDto userDto = getUserDto(emptyList());

        assertThat(UserConverter.toDto(user), is(userDto));
    }

    @Test
    public void null_fromDto_null() {
        assertNull(UserConverter.fromDto(null));
    }

    @Test
    public void fullUserObject_fromDto_correctUserObject() {
        AccountDto accountDto = getAccountDto();
        UserDto userDto = getUserDto(Arrays.asList(accountDto));

        Account account = getAccount();
        User user = getUser(Arrays.asList(account));

        assertThat(UserConverter.fromDto(userDto), is(user));
    }

    @Test
    public void userObject_with_empty_Accounts_list_fromDto_correctUserObject() {
        UserDto userDto = getUserDto(emptyList());

        User user = getUser(emptyList());

        assertThat(UserConverter.fromDto(userDto), is(user));
    }
}
