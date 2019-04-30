package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.model.Account;

public final class AccountConverter {
    public static Account fromDto(AccountDto dto) {
        if (dto == null) {
            return null;
        }

        return new Account(dto.getId(), dto.getNumber(), dto.getBalance());
    }

    public static AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        }

        return new AccountDto(account.getId(), account.getNumber(), account.getBalance());
    }
}
