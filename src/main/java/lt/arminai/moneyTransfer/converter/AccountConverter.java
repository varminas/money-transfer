package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.model.Account;

public final class AccountConverter {
    public static Account fromDto(AccountDto dto) {
        if (dto == null) {
            return null;
        }

        return Account.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .balance(dto.getBalance())
                .currency(CurrencyConverter.fromDto(dto.getCurrency()))
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public static AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        }

        return AccountDto.builder()
                .id(account.getId())
                .number(account.getNumber())
                .balance(account.getBalance())
                .currency(CurrencyConverter.toDto(account.getCurrency()))
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }
}
