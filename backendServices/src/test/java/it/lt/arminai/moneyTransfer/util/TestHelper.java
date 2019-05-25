package it.lt.arminai.moneyTransfer.util;

import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.dto.CurrencyDto;
import lt.arminai.moneyTransfer.dto.GenderDto;
import lt.arminai.moneyTransfer.dto.TransactionDto;
import lt.arminai.moneyTransfer.dto.UserDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public final class TestHelper {
    public static final String USER_ID1 = "bed6109f-ef8a-47ec-8fa4-e57c71415a10";
    public static final String USER_NAME1 = "ob123";

    public static final String ACCOUNT_ID1 = "bed6109f-ef8a-47ec-8fa4-e57c71415a12";
    public static final String ACCOUNT_ID2 = "bed6109f-ef8a-47ec-8fa4-e57c71415a13";

    public static final String TRANSACTION_ID1 = "bed6109f-ef8a-47ec-8fa4-e57c71415a16";
    public static final String TRANSACTION_ID2 = "58fdb294-92f1-469c-80fd-270a1e9596df";

    public static UserDto getUserDto1() {
        return UserDto.builder()
                .id(USER_ID1)
                .username(USER_NAME1)
                .firstName("Olivier")
                .lastName("Bruce")
                .gender(GenderDto.M)
                .phone("+37060012345")
                .accounts(Arrays.asList(getAccountDto1(), getAccountDto2()))
                .createdAt(LocalDateTime.parse("2019-01-02T20:04:35.069"))
                .updatedAt(LocalDateTime.parse("2019-01-03T02:03:04.069"))
                .build();
    }

    public static AccountDto getAccountDto1() {
        return AccountDto.builder()
                .id(ACCOUNT_ID1)
                .number("1000001")
                .currency(CurrencyDto.EUR)
                .balance(BigDecimal.valueOf(4000))
                .createdAt(LocalDateTime.parse("2019-01-03T20:04:35.069"))
                .updatedAt(LocalDateTime.parse("2019-01-04T02:03:04.069"))
                .build();
    }

    public static AccountDto getAccountDto2() {
        return AccountDto.builder()
                .id(ACCOUNT_ID2)
                .number("1000002")
                .currency(CurrencyDto.USD)
                .balance(BigDecimal.valueOf(2000))
                .createdAt(LocalDateTime.parse("2019-01-04T20:04:35.069"))
                .updatedAt(LocalDateTime.parse("2019-01-05T02:03:04.069"))
                .build();
    }

    public static TransactionDto getTransactionDto1() {
        return TransactionDto.builder()
                .id(TRANSACTION_ID1)
                .amount(BigDecimal.valueOf(30))
                .fromAccountNumber("1000001")
                .toAccountNumber("1000002")
                .createdAt(LocalDateTime.parse("2019-01-03T20:04:35.069"))
                .build();
    }

    public static TransactionDto getTransactionDto2() {
        return TransactionDto.builder()
                .id(TRANSACTION_ID2)
                .amount(BigDecimal.valueOf(15))
                .fromAccountNumber("1000001")
                .toAccountNumber("1000003")
                .createdAt(LocalDateTime.parse("2019-01-04T20:04:35.069"))
                .build();
    }
    
    public static void assertTransaction(TransactionDto actual, TransactionDto expected, boolean matchDynamicFields) {
        if (matchDynamicFields) {
            assertThat(actual.getId(), is(expected.getId()));
            assertThat(actual.getAmount(), is(expected.getAmount()));
        } else {
            assertNotNull(actual.getId());
            assertNotNull(actual.getCreatedAt());
        }
        
        assertThat(actual.getFromAccountNumber(), is(expected.getFromAccountNumber()));
        assertThat(actual.getToAccountNumber(), is(expected.getToAccountNumber()));
        assertThat(actual.getAmount(), is(expected.getAmount()));
    }

    public static void assertUser(UserDto actual, UserDto expected) {
        assertNotNull(actual);
        assertThat(actual, is(expected));
    }

    public static void assertAccount(AccountDto actual, AccountDto expected) {
        assertNotNull(actual);
        assertThat(actual, is(expected));
    }
}
