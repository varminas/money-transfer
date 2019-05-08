package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.TransactionDto;
import lt.arminai.moneyTransfer.model.Transaction;

import java.time.LocalDateTime;

public final class TransactionConverter {
    public static Transaction fromDto(TransactionDto dto) {
        if (dto == null) {
            return null;
        }

        return Transaction.builder()
                .id(dto.getId())
                .fromAccountNumber(dto.getFromAccountNumber())
                .toAccountNumber(dto.getToAccountNumber())
                .amount(dto.getAmount())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                .build();
    }

    public static TransactionDto toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        return TransactionDto.builder()
                .id(transaction.getId())
                .fromAccountNumber(transaction.getFromAccountNumber())
                .toAccountNumber(transaction.getToAccountNumber())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
