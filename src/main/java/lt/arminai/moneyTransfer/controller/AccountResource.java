package lt.arminai.moneyTransfer.controller;

import lt.arminai.moneyTransfer.converter.AccountConverter;
import lt.arminai.moneyTransfer.converter.TransactionConverter;
import lt.arminai.moneyTransfer.converter.TransactionListConverter;
import lt.arminai.moneyTransfer.dto.TransactionDto;
import lt.arminai.moneyTransfer.model.Transaction;
import lt.arminai.moneyTransfer.service.AccountService;
import lt.arminai.moneyTransfer.service.TransactionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
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
    public Response getAccount(@PathParam("accountId") long accountId) {

        return accountService.getAccount(accountId)
                .map(AccountConverter::toDto)
                .map(dto -> Response.ok(dto).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("{accountId}/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsByAccount(@PathParam("accountId") long accountId) {

        List<Transaction> transactions = transactionService.getTransactionsByAccount(accountId);

        return Response.ok(TransactionListConverter.toDto(transactions))
                .build();
    }

    @POST
    @Path("{accountId}/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMoney(@PathParam("accountId") long accountId, TransactionDto transactionDto) {

        Transaction saved = transactionService.sendMoney(TransactionConverter.fromDto(transactionDto));
        URI createdUri = URI.create("/accounts/" + accountId + "/transactions");

        return Response.created(createdUri)
                .entity(saved)
                .build();
    }
}
