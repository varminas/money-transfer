package lt.arminai.moneyTransfer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private GenderDto gender;
    private List<AccountDto> accounts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
