package lt.arminai.moneyTransfer.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lt.arminai.moneyTransfer.dto.GenderDto;
import lt.arminai.moneyTransfer.dto.UserDto;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserResourceIT {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USER_ID = "bed6109f-ef8a-47ec-8fa4-e57c71415a10";

    private static String URL;

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeClass
    public static void init() {

        String port = System.getProperty("liberty.test.port");
        URL = "http://localhost:" + port + "/banking/users/" + USER_ID;
    }

    @Test
    public void getUserAndAccounts() throws Exception {
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(URL);
        try {
            int statusCode = client.executeMethod(method);

            assertEquals("HTTP GET failed", HttpStatus.SC_OK, statusCode);

            UserDto userDto = getEntity(method.getResponseBodyAsString(), UserDto.class);

            assertThat(userDto.getId(), is(USER_ID));
            assertThat(userDto.getFirstName(), is("Vytautas"));
            assertThat(userDto.getLastName(), is("Arminas"));
            assertThat(userDto.getGender(), is(GenderDto.M));
            assertThat(userDto.getPhone(), is("+37060012345"));
            assertNotNull(userDto.getCreatedAt());
            assertNotNull(userDto.getUpdatedAt());
            assertThat(userDto.getAccounts().size(), is(2));
        } finally {
            method.releaseConnection();
        }
    }

    private <T> T getEntity(String responseAsString, Class<T> clazz) throws IOException {
        return objectMapper.readValue(responseAsString, clazz);
    }
}
