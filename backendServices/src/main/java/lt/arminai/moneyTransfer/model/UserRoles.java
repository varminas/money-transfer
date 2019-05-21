package lt.arminai.moneyTransfer.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_roles")
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserRoles extends BasePersistentEntity {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public UserRoles(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String username, Role role) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.role = role;
    }
}
