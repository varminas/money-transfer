package lt.arminai.moneyTransfer.controller;

import lt.arminai.moneyTransfer.converter.AccountConverter;
import lt.arminai.moneyTransfer.service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/account")
public class AccountResource {

    @Inject
    private AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount() {
        // TODO get from SecurityContext
        int accountId = 1;

        return accountService.getAccount(accountId)
                .map(AccountConverter::toDto)
                .map(dto -> Response.ok(dto).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}
