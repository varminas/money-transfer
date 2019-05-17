package lt.arminai.moneyTransfer.controller;

import lombok.NoArgsConstructor;
import lt.arminai.moneyTransfer.converter.*;
import lt.arminai.moneyTransfer.dto.TransactionDto;
import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.model.BasePersistentEntity;
import lt.arminai.moneyTransfer.model.Transaction;
import lt.arminai.moneyTransfer.model.User;
import lt.arminai.moneyTransfer.service.TransactionService;
import lt.arminai.moneyTransfer.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequestScoped
@Path("/users")
@NoArgsConstructor
public class UserResource {

    private UserService userService;
    private TransactionService transactionService;

    @Inject
    public UserResource(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GET
    @Path("{userId}")
    @RolesAllowed({"ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") String userId) {

        return userService.getUser(userId)
                .map(UserConverter::toDto)
                .map(dto -> Response.ok(dto).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("{userId}/accounts")
    @RolesAllowed({"ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccounts(@PathParam("userId") String userId) {
        List<Account> accounts = userService.getUser(userId)
                .map(User::getAccounts)
                .orElse(Collections.emptyList());

        accounts.sort(Comparator.comparing(BasePersistentEntity::getCreatedAt));

        return Response.ok(AccountListConverter.toDto(accounts))
                .build();
    }

    @GET
    @Path("{userId}/accounts/{accountId}")
    @RolesAllowed({"ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(
            @PathParam("userId") String userId,
            @PathParam("accountId") String accountId
    ) {
        return userService.getUser(userId)
                .map(User::getAccounts)
                .get()
                .stream()
                .filter(account -> accountId.equals(account.getId()))
                .map(AccountConverter::toDto)
                .map(dto -> Response.ok(dto).build())
                .findFirst()
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("{userId}/accounts/{accountId}/transactions")
    @RolesAllowed({"ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsByAccount(
            @PathParam("userId") String userId,
            @PathParam("accountId") String accountId
    ) {
        List<Transaction> transactions = transactionService.getTransactionsByAccount(userId, accountId);

        return Response.ok(TransactionListConverter.toDto(transactions)).build();
    }

    @POST
    @Path("{userId}/accounts/transactions")
    @RolesAllowed({"ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response transfer(
            @PathParam("userId") String userId,
            TransactionDto transactionDto
    ) {
        Transaction saved = transactionService.transfer(userId, TransactionConverter.fromDto(transactionDto));
        URI createdUri = URI.create("/users" + userId + "/accounts/transactions");

        return Response.created(createdUri)
                .entity(saved)
                .build();
    }
}
