package lt.arminai.moneyTransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private int id;
    private String number;
    private long balance;
}
