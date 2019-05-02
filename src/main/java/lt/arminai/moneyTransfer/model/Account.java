package lt.arminai.moneyTransfer.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Account")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Account {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "balance")
    private long balance;
}
