package lt.arminai.moneyTransfer.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionDtoList {
    private List<TransactionDto> transactions;
}
