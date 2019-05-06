package lt.arminai.moneyTransfer.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private Long id;
    private int fromAccountId;
    private int toAccountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
