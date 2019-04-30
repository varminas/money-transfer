package lt.arminai.moneyTransfer.model;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
public class Account {
    private String id;
    private String number;
    private long balance;
}
