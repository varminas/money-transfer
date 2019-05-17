package lt.arminai.moneyTransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private GenderDto gender;
    private List<AccountDto> accounts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
