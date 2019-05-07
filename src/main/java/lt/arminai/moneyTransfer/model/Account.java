package lt.arminai.moneyTransfer.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Account")
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Account extends BasePersistentEntity {

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Builder
    public Account(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String number, BigDecimal balance, Currency currency) {
        super(id, createdAt, updatedAt);
        this.number = number;
        this.balance = balance;
        this.currency = currency;
    }
}
