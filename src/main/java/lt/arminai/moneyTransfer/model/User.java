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
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Account> accounts;

    @Builder
    public User(LocalDateTime createdAt, LocalDateTime updatedAt, Long id, String firstName, String lastName,
                Gender gender, String phone, List<Account> accounts) {
        super(createdAt, updatedAt);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.accounts = accounts;
    }
}
