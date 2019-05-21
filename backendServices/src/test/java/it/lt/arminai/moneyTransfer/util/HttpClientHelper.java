package it.lt.arminai.moneyTransfer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class HttpClientHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static final Response processRequest(String url, String method, String payload,
                                                String authHeader, Cookie cookie) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url);
        Builder builder = target.request();
        builder.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        if (authHeader != null) {
            builder.header(HttpHeaders.AUTHORIZATION, authHeader);
        }
        
        if (cookie != null) {
            builder.cookie(cookie);
        }
        
        return (payload != null)
                ? builder.build(method, Entity.json(payload)).invoke()
                : builder.build(method).invoke();
    }

    public <T> T getEntity(String responseAsString, Class<T> clazz) throws IOException {
        return objectMapper.readValue(responseAsString, clazz);
    }

    public String objectToString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
