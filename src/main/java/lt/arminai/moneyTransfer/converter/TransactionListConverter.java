package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.TransactionDto;
import lt.arminai.moneyTransfer.dto.TransactionDtoList;
import lt.arminai.moneyTransfer.model.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public final class TransactionListConverter {
    public static TransactionDtoList toDto(List<Transaction> transactions) {
        List<TransactionDto> transactionDtos = transactions.stream()
                .map(TransactionConverter::toDto)
                .collect(Collectors.toList());

        TransactionDtoList transactionDtoList = new TransactionDtoList();
        transactionDtoList.setTransactions(transactionDtos);

        return transactionDtoList;
    }
}
