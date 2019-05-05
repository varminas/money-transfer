package lt.arminai.moneyTransfer.controller;

import lt.arminai.moneyTransfer.converter.AccountConverter;
import lt.arminai.moneyTransfer.converter.TransactionListConverter;
import lt.arminai.moneyTransfer.model.Transaction;
import lt.arminai.moneyTransfer.service.AccountService;
import lt.arminai.moneyTransfer.service.TransactionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/accounts")
public class AccountResource {

    @Inject
    private AccountService accountService;

    @Inject
    private TransactionService transactionService;

    @GET
    @Path("{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("accountId") int accountId) {

        return accountService.getAccount(accountId)
                .map(AccountConverter::toDto)
                .map(dto -> Response.ok(dto).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("{accountId}/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsByAccount(@PathParam("accountId") int accountId) {

        List<Transaction> transactions = transactionService.getTransactionsByAccount(accountId);

        return Response.ok(TransactionListConverter.toDto(transactions))
                .build();
    }
}
