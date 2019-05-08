package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.dto.AccountDtoList;
import lt.arminai.moneyTransfer.model.Account;

import java.util.List;
import java.util.stream.Collectors;

public final class AccountListConverter {
    public static AccountDtoList toDto(List<Account> accounts) {
        List<AccountDto> accountDtos = accounts.stream()
                .map(AccountConverter::toDto)
                .collect(Collectors.toList());

        AccountDtoList accountDtoList = new AccountDtoList();
        accountDtoList.setAccounts(accountDtos);

        return accountDtoList;
    }
}
