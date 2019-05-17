package lt.arminai.moneyTransfer.controller;

import lombok.NoArgsConstructor;
import lt.arminai.moneyTransfer.dto.AuthDto;
import lt.arminai.moneyTransfer.model.AuthPojo;
import lt.arminai.moneyTransfer.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/tokens")
@PermitAll
@NoArgsConstructor
public class AuthResource {
    private static final Logger logger = LoggerFactory.getLogger(AuthResource.class);

    private SecurityContext securityContext;
    private AuthService authService;

    @Inject
    public AuthResource(SecurityContext securityContext, AuthService authService) {
        this.securityContext = securityContext;
        this.authService = authService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJwt() {
        if (securityContext.isCallerInRole("USER") || securityContext.isCallerInRole("ADMIN")) {
            String name = securityContext.getCallerPrincipal().getName();
            AuthPojo authPojo = authService.createJwt(name);

            return Response.ok(new AuthDto(authPojo.getJwt(), authPojo.getUserId())).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
