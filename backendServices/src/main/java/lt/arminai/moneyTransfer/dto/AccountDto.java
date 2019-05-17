package lt.arminai.moneyTransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String id;
    private String number;
    private BigDecimal balance;
    private CurrencyDto currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
