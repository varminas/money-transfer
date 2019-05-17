package lt.arminai.moneyTransfer.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
