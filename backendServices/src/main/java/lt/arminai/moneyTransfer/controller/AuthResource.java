package lt.arminai.moneyTransfer.controller;

import lombok.NoArgsConstructor;
import lt.arminai.moneyTransfer.dto.AuthDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

//@RequestScoped
//@Path("/auth")
//@PermitAll
@NoArgsConstructor
public class AuthResource {
    private static final Logger logger = LoggerFactory.getLogger(AuthResource.class);
    @Inject
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJwt() {
        logger.info("getJwt(), {}", securityContext.getUserPrincipal());
        if (securityContext.isUserInRole("USER")) {
            return Response.ok(new AuthDto("abc")).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
