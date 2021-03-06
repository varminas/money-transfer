package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.AccountDto;
import lt.arminai.moneyTransfer.dto.UserDto;
import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.model.User;

import java.util.List;
import java.util.stream.Collectors;

public final class UserConverter {
    public static User fromDto(UserDto dto) {
        if (dto == null) {
            return null;
        }

        List<Account> accounts = dto.getAccounts().stream()
                .map(AccountConverter::fromDto)
                .collect(Collectors.toList());

        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .gender(GenderConverter.fromDto(dto.getGender()))
                .accounts(accounts)
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();

    }

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        List<AccountDto> accounts = user.getAccounts().stream()
                .map(AccountConverter::toDto)
                .collect(Collectors.toList());

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .gender(GenderConverter.toDto(user.getGender()))
                .accounts(accounts)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
