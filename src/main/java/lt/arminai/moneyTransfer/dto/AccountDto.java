package lt.arminai.moneyTransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private String id;
    private String number;
    private long balance;
}
