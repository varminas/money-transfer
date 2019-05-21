package it.lt.arminai.moneyTransfer;

import it.lt.arminai.moneyTransfer.util.HttpClientHelper;
import lt.arminai.moneyTransfer.dto.AuthDto;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AuthResourceIT {
    private static final String BASE_URL = "https://localhost:" +
            System.getProperty("liberty.test.ssl.port") + "/tokens";

    private static HttpClientHelper httpClientHelper;

    @BeforeClass
    public static void init() {
        httpClientHelper = new HttpClientHelper();
    }

    @Test
    public void getAuthTokenForValidCredentials() throws IOException {
        Response response = HttpClientHelper.processRequest(BASE_URL, "GET", null,
                "Basic b2IxMjM6b2IxMjNwd2Q=", null);
        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.OK.getStatusCode()));

        AuthDto authDto = httpClientHelper.getEntity(response.readEntity(String.class), AuthDto.class);
        assertThat(authDto.getUserId(), is("bed6109f-ef8a-47ec-8fa4-e57c71415a10"));
        assertNotNull(authDto.getJwt());
    }

    @Test
    public void getAuthTokenForInValidCredentials() {
        Response response = HttpClientHelper.processRequest(BASE_URL, "GET", null,
                "Basic dummy=", null);
        assertThat("HTTP GET failed", response.getStatus(), is(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
    }
}
