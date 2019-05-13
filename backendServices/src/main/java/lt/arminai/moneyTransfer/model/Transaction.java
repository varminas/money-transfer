package lt.arminai.moneyTransfer.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.eclipse.persistence.internal.helper.StringHelper.isBlank;

@Entity
@Table(name = "Transaction")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Transaction {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "from_account_number", nullable = false)
    private String fromAccountNumber;

    @Column(name = "to_account_number", nullable = false)
    private String toAccountNumber;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void generateId() {
        if (isBlank(this.id)) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
