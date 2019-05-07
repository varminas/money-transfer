package lt.arminai.moneyTransfer.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AccountDto {
    private String id;
    private String number;
    private BigDecimal balance;
    private CurrencyDto currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
