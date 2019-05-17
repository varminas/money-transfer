package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.CurrencyDto;
import lt.arminai.moneyTransfer.model.Currency;

public final class CurrencyConverter {
    public static Currency fromDto(CurrencyDto dto) {
        if (dto == null) {
            return null;
        }

        return Currency.valueOf(dto.toString());
    }

    public static CurrencyDto toDto(Currency currency) {
        if (currency == null) {
            return null;
        }

        return CurrencyDto.valueOf(currency.toString());
    }
}
