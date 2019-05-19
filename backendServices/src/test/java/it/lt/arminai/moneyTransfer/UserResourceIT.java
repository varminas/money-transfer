package it.lt.arminai.moneyTransfer;

import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.dto.AccountDtoList;
import lt.arminai.moneyTransfer.dto.TransactionDtoList;
import lt.arminai.moneyTransfer.dto.UserDto;
import it.lt.arminai.moneyTransfer.util.HttpClientHelper;
import it.lt.arminai.moneyTransfer.util.JwtVerifier;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static it.lt.arminai.moneyTransfer.TestHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class UserResourceIT {
    private static String URL;

    private String authHeader;

    @BeforeClass
    public static void init() {
        String port = System.getProperty("liberty.test.ssl.port");
        URL = "https://localhost:" + port + "/banking/users/" + USER_ID1;
    }

    @Before
    public void setup() throws Exception {
        authHeader = "Bearer " + new JwtVerifier().createAdminJwt(USER_NAME1);
    }

    @Test
    public void getUserAndAccounts() {
        Response response = HttpClientHelper.processRequest(URL, "GET", null, authHeader);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));

        UserDto actual = response.readEntity(UserDto.class);
        UserDto expected = getUserDto1();
        assertUser(actual, expected);
    }

    @Test
    public void getUserAccountById() {
        Response response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1, "GET", null, authHeader);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));

        AccountDto actual = response.readEntity(AccountDto.class);
        AccountDto expected = getAccountDto1();
        assertAccount(actual, expected);

    }

    @Test
    public void getAllUserAccounts() {
        Response response = HttpClientHelper.processRequest(URL + "/accounts", "GET", null, authHeader);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));

        AccountDtoList accounts = response.readEntity(AccountDtoList.class);
        assertThat(accounts.getAccounts().size(), is(2));
        assertAccount(accounts.getAccounts().get(0), getAccountDto1());
        assertAccount(accounts.getAccounts().get(1), getAccountDto2());
    }

    @Test
    public void getTransactionsForAccount() {
        Response response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1 + "/transactions", "GET", null, authHeader);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));

        TransactionDtoList transactions = response.readEntity(TransactionDtoList.class);

        assertThat(transactions.getTransactions().size(), is(2));

        assertTransaction(transactions.getTransactions().get(0), getTransactionDto1());
        assertTransaction(transactions.getTransactions().get(1), getTransactionDto2());
    }
}
