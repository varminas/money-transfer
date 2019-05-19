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

    private static HttpClientHelper httpClientHelper;
    private String authHeader;

    @BeforeClass
    public static void init() {
        httpClientHelper = new HttpClientHelper();

        String port = System.getProperty("liberty.test.ssl.port");
        URL = "https://localhost:" + port + "/banking/users/" + USER_ID1;
    }

    @Before
    public void setup() throws Exception {
        authHeader = "Bearer " + new JwtVerifier().createAdminJwt(USER_NAME1);
//        authHeader = "Bearer " + "eyJraWQiOiJFTDNhZ1RvUHk2SFAyQ2RwMDdsQUZ1dUFicnZZNHlxSDZNeWlpWnZSSkxvIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJ0b2tlbl90eXBlIjoiQmVhcmVyIiwiYXVkIjoic2ltcGxlYXBwIiwic3ViIjoib2IxMjMiLCJ1cG4iOiJvYjEyMyIsImdyb3VwcyI6WyJBRE1JTiIsIlVTRVIiXSwidXNlcklkIjoiYmVkNjEwOWYtZWY4YS00N2VjLThmYTQtZTU3YzcxNDE1YTEwIiwiaXNzIjoiaHR0cDovL29wZW5saWJlcnR5LmlvIiwiZXhwIjoxNTU4MzYyODc3LCJpYXQiOjE1NTgyNzY0Nzd9.z3rh3_jEEAkVuJ8M0K-zoT2u4IxsaapdEld88cQA7wd4S-qMZdF7w5UvIQm8T8oY9tmJMA1pESMBvHR0pgxI1y3fFCi6A2VTzR54gGbqFRpC5E5tNG8RTUx996XPfn_pHNr85ZRZelAyWECLUO5YzjnW5ex8dHPGa8skTh7VjKs7k-7DE3yMJSs3SBpcz5BDZC5MYP1mgcgmlGo-uA6gd72fV5caCFHjSfbiahUsRr3IRodOy7RGV0TA11pEX1WTDGoeXv_3WH0h811-OEA4oyKm6osLwelMNNYCzT91t3YypKSahAGjx4NXo188Bmj88FpWDN_RpEBLxXB2Ylhb2A";
    }

    @Test
    public void getUserAndAccounts() {
        Response response = httpClientHelper.processRequest(URL, "GET", null, authHeader);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));

        UserDto actual = response.readEntity(UserDto.class);
        UserDto expected = getUserDto1();
        assertUser(actual, expected);
    }

    @Test
    public void getUserAccountById() {
        Response response = httpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1, "GET", null, authHeader);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));

        AccountDto actual = response.readEntity(AccountDto.class);
        AccountDto expected = getAccountDto1();
        assertAccount(actual, expected);

    }

    @Test
    public void getAllUserAccounts() {
        Response response = httpClientHelper.processRequest(URL + "/accounts", "GET", null, authHeader);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));

        AccountDtoList accounts = response.readEntity(AccountDtoList.class);
        assertThat(accounts.getAccounts().size(), is(2));
        assertAccount(accounts.getAccounts().get(0), getAccountDto1());
        assertAccount(accounts.getAccounts().get(1), getAccountDto2());
    }

    @Test
    public void getTransactionsForAccount() {
        Response response = httpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1 + "/transactions", "GET", null, authHeader);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));

        TransactionDtoList transactions = response.readEntity(TransactionDtoList.class);

        assertThat(transactions.getTransactions().size(), is(2));

        assertTransaction(transactions.getTransactions().get(0), getTransactionDto1());
        assertTransaction(transactions.getTransactions().get(1), getTransactionDto2());
    }
}
