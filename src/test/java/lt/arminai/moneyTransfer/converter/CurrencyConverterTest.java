package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.CurrencyDto;
import lt.arminai.moneyTransfer.model.Currency;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CurrencyConverterTest {
    @Test
    public void null_toDto_null() {
        assertNull(CurrencyConverter.toDto(null));
    }

    @Test
    public void EUR_toDto_EUR() {
        assertThat(CurrencyConverter.toDto(Currency.EUR), is(CurrencyDto.EUR));
    }

    @Test
    public void null_fromDto_null() {
        assertNull(CurrencyConverter.fromDto(null));
    }

    @Test
    public void EUR_fromDto_EUR() {
        assertThat(CurrencyConverter.fromDto(CurrencyDto.EUR), is(Currency.EUR));
    }
}
