package lt.arminai.moneyTransfer.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {
    private int id;
    private int fromAccountId;
    private int toAccountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
