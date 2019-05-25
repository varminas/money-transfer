package it.lt.arminai.moneyTransfer;

import it.lt.arminai.moneyTransfer.util.HttpClientHelper;
import it.lt.arminai.moneyTransfer.util.JwtVerifier;
import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.dto.AccountDtoList;
import lt.arminai.moneyTransfer.dto.TransactionDto;
import lt.arminai.moneyTransfer.dto.TransactionDtoList;
import lt.arminai.moneyTransfer.dto.UserDto;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;

import static it.lt.arminai.moneyTransfer.util.TestHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserResourceIT {
    private static final String BASE_URL = "https://localhost:" +
        System.getProperty("liberty.test.ssl.port");
    private static final String URL = BASE_URL + "/banking/users/" + USER_ID1;
    
    private String authHeader;
    private static Cookie cookie;
    private static HttpClientHelper httpClientHelper;
    
    @BeforeClass
    public static void init() {
        httpClientHelper = new HttpClientHelper();
        Response authResponse = authenticate();
    
        getCookieFromResponse(authResponse);
    }
    
    // TODO Better use authHeader instead of Cookie, if possible
    @Before
    public void setup() throws Exception {
        authHeader = "Bearer " + new JwtVerifier().createAdminJwt(USER_NAME1);
    }

    @Test
    @Ignore(value = "Authorization with provided JWT does not work, 401 instead 200")
    public void getUserAndAccounts_withJwt_authorized() throws IOException {
        Response response = HttpClientHelper.processRequest(URL, "GET", null, authHeader, null);

        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));

        UserDto actual = httpClientHelper.getEntity(response.readEntity(String.class), UserDto.class);
        UserDto expected = getUserDto1();
        assertUser(actual, expected);
    }
    
    @Test
    public void getUserAndAccounts_authorized() throws IOException {
        Response response = HttpClientHelper.processRequest(URL, "GET", null, null, cookie);
        
        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));
        
        UserDto actual = httpClientHelper.getEntity(response.readEntity(String.class), UserDto.class);
        UserDto expected = getUserDto1();
        assertUser(actual, expected);
    }

    @Test
    public void getUserAndAccounts_unauthorized() {
        Response response = HttpClientHelper.processRequest(URL, "GET", null, null, null);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
    }
    
    @Test
    public void getUserAccountById() throws IOException {
        Response response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1, "GET", null, null, cookie);
        
        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));
        
        AccountDto actual = httpClientHelper.getEntity(response.readEntity(String.class), AccountDto.class);
        AccountDto expected = getAccountDto1();
        assertAccount(actual, expected);
        
    }

    @Test
    public void getUserAccountById_unauthorized() {
        Response response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1, "GET", null, null, null);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
    }
    
    @Test
    public void getAllUserAccounts() throws IOException {
        Response response = HttpClientHelper.processRequest(URL + "/accounts", "GET", null, null, cookie);
        
        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));
        
        AccountDtoList accounts = httpClientHelper.getEntity(response.readEntity(String.class), AccountDtoList.class);
        assertThat(accounts.getAccounts().size(), is(2));
        assertAccount(accounts.getAccounts().get(0), getAccountDto1());
        assertAccount(accounts.getAccounts().get(1), getAccountDto2());
    }

    @Test
    public void getAllUserAccounts_unauthorized() {
        Response response = HttpClientHelper.processRequest(URL + "/accounts", "GET", null, null, null);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
    }
    
    @Test
    public void getTransactionsForAccount() throws IOException {
        Response response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1 + "/transactions", "GET", null, null, cookie);
        
        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));
        
        TransactionDtoList transactions = httpClientHelper.getEntity(response.readEntity(String.class), TransactionDtoList.class);

        assertThat(transactions.getTransactions().size(), is(2));

        assertTransaction(transactions.getTransactions().get(0), getTransactionDto1(), true);
        assertTransaction(transactions.getTransactions().get(1), getTransactionDto2(), true);
    }

    @Test
    public void getTransactionsForAccount_unauthorized() {
        Response response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1 + "/transactions", "GET",
                null, null, null);

        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
    }

    @Test
    public void transferBetweenOwnAccounts() throws Exception {
        // 1. Get initial balance of accounts
        // 1.1 Account 'FROM'
        Response response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1, "GET", null, null, cookie);
        
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        AccountDto actual = httpClientHelper.getEntity(response.readEntity(String.class), AccountDto.class);
        assertThat(actual.getBalance(), is(BigDecimal.valueOf(4000)));
    
        // 1.2 Account 'FROM'
        response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID2, "GET", null, null, cookie);
    
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        actual = httpClientHelper.getEntity(response.readEntity(String.class), AccountDto.class);
        assertThat(actual.getBalance(), is(BigDecimal.valueOf(2000)));
        
        // 2. Make a money transfer
        response = HttpClientHelper.processRequest(URL + "/accounts/transactions",
                "POST", httpClientHelper.objectToString(getTransactionDto()), null, cookie);

        assertThat(response.getStatus(), is(Response.Status.CREATED.getStatusCode()));
        TransactionDto newTransaction = httpClientHelper.getEntity(response.readEntity(String.class), TransactionDto.class);
        assertTransaction(newTransaction, getTransactionDto(), false);
        
        // 3. Get balance of accounts after transfer
        // 3.1 Account 'FROM'
        response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID1, "GET", null, null, cookie);
    
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        actual = httpClientHelper.getEntity(response.readEntity(String.class), AccountDto.class);
        assertThat(actual.getBalance(), is(BigDecimal.valueOf(3990)));
    
        // 3.2 Account 'FROM'
        response = HttpClientHelper.processRequest(URL + "/accounts/" + ACCOUNT_ID2, "GET", null, null, cookie);
    
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        actual = httpClientHelper.getEntity(response.readEntity(String.class), AccountDto.class);
        assertThat(actual.getBalance(), is(BigDecimal.valueOf(2010)));
    }
    
    @Test
    public void transferBetweenOwnAccounts_unauthorized() throws Exception {
        Response response = HttpClientHelper.processRequest(URL + "/accounts/transactions",
            "POST", httpClientHelper.objectToString(getTransactionDto()), null, null);
        
        assertThat(response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
    }
    
    private TransactionDto getTransactionDto() {
        return TransactionDto.builder()
                    .fromAccountNumber("1000001")
                    .toAccountNumber("1000002")
                    .amount(BigDecimal.TEN)
                    .build();
    }
    
    private static Response authenticate() {
        Response authResponse = HttpClientHelper.processRequest(BASE_URL + "/tokens", "GET", null,
            "Basic b2IxMjM6b2IxMjNwd2Q=", null);
        assertThat("HTTP GET failed", authResponse.getStatus(), is(Response.Status.OK.getStatusCode()));
        return authResponse;
    }
    
    private static void getCookieFromResponse(Response authResponse) {
        String setCookieHeader = authResponse.getHeaderString("Set-Cookie");
        final String cookieName = "LtpaToken2";
        
        String[] parts = setCookieHeader.split(";");
        String nameValue = parts[0];
        String value = nameValue.replace(cookieName + "=", "");
        
        cookie = new Cookie(cookieName, value);
    }
}
