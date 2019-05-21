package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.dto.AuthDto;
import lt.arminai.moneyTransfer.dto.CurrencyDto;
import lt.arminai.moneyTransfer.dto.GenderDto;
import lt.arminai.moneyTransfer.dto.UserDto;
import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.model.AuthPojo;
import lt.arminai.moneyTransfer.model.Currency;
import lt.arminai.moneyTransfer.model.Gender;
import lt.arminai.moneyTransfer.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public final class TestData {
    public static Account getAccount() {
        return Account.builder()
                .id("58fdb294-92f1-469c-80fd-270a1e9596df")
                .number("123")
                .balance(BigDecimal.TEN)
                .currency(Currency.EUR)
                .createdAt(LocalDateTime.of(2019, 1, 2, 3, 4, 5))
                .createdAt(LocalDateTime.of(2019, 1, 2, 3, 4, 6))
                .build();
    }

    public static AccountDto getAccountDto() {
        return AccountDto.builder()
                .id("58fdb294-92f1-469c-80fd-270a1e9596df")
                .number("123")
                .balance(BigDecimal.TEN)
                .currency(CurrencyDto.EUR)
                .createdAt(LocalDateTime.of(2019, 1, 2, 3, 4, 5))
                .createdAt(LocalDateTime.of(2019, 1, 2, 3, 4, 6))
                .build();
    }

    public static User getUser(List<Account> accounts) {
        return User.builder()
                .id("58fdb294-92f1-469c-80fd-270a1e9596df")
                .username("123456")
                .firstName("Fname")
                .lastName("Lname")
                .accounts(accounts)
                .phone("+37060012345")
                .gender(Gender.M)
                .createdAt(LocalDateTime.of(2019, 2, 2, 3, 4, 5))
                .updatedAt(LocalDateTime.of(2019, 2, 2, 3, 4, 5))
                .build();
    }

    public static UserDto getUserDto(List<AccountDto> accounts) {
        return UserDto.builder()
                .id("58fdb294-92f1-469c-80fd-270a1e9596df")
                .username("123456")
                .firstName("Fname")
                .lastName("Lname")
                .phone("+37060012345")
                .gender(GenderDto.M)
                .accounts(accounts)
                .createdAt(LocalDateTime.of(2019, 2, 2, 3, 4, 5))
                .updatedAt(LocalDateTime.of(2019, 2, 2, 3, 4, 5))
                .build();
    }

    public static AuthPojo getAuthPojo() {
        return new AuthPojo("jwtabc", "123456");
    }

    public static AuthDto getAuthDto() {
        return new AuthDto("jwtabc", "123456");
    }
}
