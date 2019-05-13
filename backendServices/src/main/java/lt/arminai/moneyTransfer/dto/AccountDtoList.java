package lt.arminai.moneyTransfer.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDtoList {
    private List<AccountDto> accounts;
}
