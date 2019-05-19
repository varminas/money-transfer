package it.lt.arminai.moneyTransfer.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public final class HttpClientHelper {

    public final Response processRequest(String url, String method, String payload,
                                          String authHeader) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url);
        Builder builder = target.request();
        builder.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        if (authHeader != null) {
            builder.header(HttpHeaders.AUTHORIZATION, authHeader);
        }
        return (payload != null)
                ? builder.build(method, Entity.json(payload)).invoke()
                : builder.build(method).invoke();
    }

}
