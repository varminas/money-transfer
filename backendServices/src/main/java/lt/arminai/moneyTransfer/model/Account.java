package lt.arminai.moneyTransfer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Account")
@Getter
@NoArgsConstructor
@ToString
public class Account extends BasePersistentEntity {

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "balance", nullable = false)
    @Setter
    private BigDecimal balance;

    @Column(name = "currency", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private Long version;

    @Builder
    public Account(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String number, BigDecimal balance, Currency currency) {
        super(id, createdAt, updatedAt);
        this.number = number;
        this.balance = balance;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(number, account.number) &&
                Objects.equals(balance, account.balance) &&
                currency == account.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, balance, currency);
    }
}
