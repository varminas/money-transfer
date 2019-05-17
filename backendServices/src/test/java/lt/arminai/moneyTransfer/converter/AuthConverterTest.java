package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.AuthDto;
import lt.arminai.moneyTransfer.model.AuthPojo;
import org.junit.Test;

import static lt.arminai.moneyTransfer.converter.TestData.getAuthDto;
import static lt.arminai.moneyTransfer.converter.TestData.getAuthPojo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AuthConverterTest {
    @Test
    public void null_toDto_null() {
        assertNull(AuthConverter.toDto(null));
    }

    @Test
    public void fullAccountObject_toDto_correctAccountObject() {
        AuthPojo authPojo = getAuthPojo();

        AuthDto authDto = getAuthDto();

        assertThat(AuthConverter.toDto(authPojo), is(authDto));
    }
}
