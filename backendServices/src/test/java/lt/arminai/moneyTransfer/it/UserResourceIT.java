package lt.arminai.moneyTransfer.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.dto.AccountDtoList;
import lt.arminai.moneyTransfer.dto.TransactionDtoList;
import lt.arminai.moneyTransfer.dto.UserDto;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static lt.arminai.moneyTransfer.it.TestHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Ignore
public class UserResourceIT {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static String URL;

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeClass
    public static void init() {
        String port = System.getProperty("liberty.test.port");
        URL = "http://localhost:" + port + "/banking/users/" + USER_ID1;
    }

    @Test
    public void getUserAndAccounts() throws Exception {
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(URL);
        try {
            int statusCode = client.executeMethod(method);

            assertEquals("HTTP GET failed", HttpStatus.SC_OK, statusCode);

            UserDto actual = getEntity(method.getResponseBodyAsString(), UserDto.class);
            UserDto expected = getUserDto1();
            assertUser(actual, expected);
        } finally {
            method.releaseConnection();
        }
    }

    @Test
    public void getUserAccountById() throws Exception {
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(URL + "/accounts/" + ACCOUNT_ID1);
        try {
            int statusCode = client.executeMethod(method);

            assertThat("HTTP GET failed", HttpStatus.SC_OK, is(statusCode));

            AccountDto actual = getEntity(method.getResponseBodyAsString(), AccountDto.class);
            AccountDto expected = getAccountDto1();
            assertAccount(actual, expected);
        } finally {
            method.releaseConnection();
        }
    }

    @Test
    public void getAllUserAccounts() throws Exception {
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(URL + "/accounts");
        try {
            int statusCode = client.executeMethod(method);

            assertThat("HTTP GET failed", HttpStatus.SC_OK, is(statusCode));

            AccountDtoList accounts = getEntity(method.getResponseBodyAsString(), AccountDtoList.class);
            assertThat(accounts.getAccounts().size(), is(2));
            assertAccount(accounts.getAccounts().get(0), getAccountDto1());
            assertAccount(accounts.getAccounts().get(1), getAccountDto2());
        } finally {
            method.releaseConnection();
        }
    }

    @Test
    public void getTransactionsForAccount() throws Exception {
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(URL + "/accounts/" + ACCOUNT_ID1 + "/transactions");
        try {
            int statusCode = client.executeMethod(method);

            assertThat("HTTP GET failed", HttpStatus.SC_OK, is(statusCode));

            TransactionDtoList transactions = getEntity(method.getResponseBodyAsString(), TransactionDtoList.class);

            assertThat(transactions.getTransactions().size(), is(2));

            assertTransaction(transactions.getTransactions().get(0), getTransactionDto1());
            assertTransaction(transactions.getTransactions().get(1), getTransactionDto2());
        } finally {
            method.releaseConnection();
        }
    }

    private <T> T getEntity(String responseAsString, Class<T> clazz) throws IOException {
        return objectMapper.readValue(responseAsString, clazz);
    }
}
