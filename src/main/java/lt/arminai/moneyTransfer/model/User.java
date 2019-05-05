package lt.arminai.moneyTransfer.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "User")
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User extends BasePersistentEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Account> accounts;

    @Builder
    public User(LocalDateTime createdAt, LocalDateTime updatedAt, int id, String firstName, String lastName, List<Account> accounts) {
        super(createdAt, updatedAt);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
    }
}
