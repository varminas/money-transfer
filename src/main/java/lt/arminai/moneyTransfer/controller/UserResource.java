package lt.arminai.moneyTransfer.controller;

import lombok.NoArgsConstructor;
import lt.arminai.moneyTransfer.converter.*;
import lt.arminai.moneyTransfer.dto.TransactionDto;
import lt.arminai.moneyTransfer.dto.exception.ErrorMessage;
import lt.arminai.moneyTransfer.model.Account;
import lt.arminai.moneyTransfer.model.Transaction;
import lt.arminai.moneyTransfer.model.User;
import lt.arminai.moneyTransfer.service.AccountService;
import lt.arminai.moneyTransfer.service.TransactionService;
import lt.arminai.moneyTransfer.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/users")
@NoArgsConstructor
public class UserResource {

    private UserService userService;
    private AccountService accountService;
    private TransactionService transactionService;

    @Inject
    public UserResource(UserService userService, AccountService accountService, TransactionService transactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") String userId) {

        return userService.getUser(userId)
                .map(UserConverter::toDto)
                .map(dto -> Response.ok(dto).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("{userId}/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccounts(@PathParam("userId") String userId) {
        List<Account> accounts = userService.getUser(userId)
                .map(User::getAccounts)
                .orElse(Collections.emptyList());

        return Response.ok(AccountListConverter.toDto(accounts))
                .build();
    }

    @GET
    @Path("{userId}/accounts/{accountId}")
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsByAccount(
            @PathParam("userId") String userId,
            @PathParam("accountId") String accountId
    ) {
        Optional<User> user = userService.getUser(userId);

        if (!user.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessage(Response.Status.NOT_FOUND.getStatusCode(), "User not found " + userId))
                    .build();
        }

        return user.get().getAccounts().stream()
                .filter(account -> accountId.equals(account.getId()))
                .findFirst()
                .map(account -> transactionService.getTransactionsByAccount(account.getNumber()))
                .map(transactions -> Response.ok(TransactionListConverter.toDto(transactions))
                        .build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorMessage(Response.Status.NOT_FOUND.getStatusCode(),
                                "Account not found " + accountId))
                        .build()
                );
    }

    @POST
    @Path("{userId}/accounts/{accountId}/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response transfer(
            @PathParam("userId") String userId,
            @PathParam("accountId") String accountId,
            TransactionDto transactionDto
    ) {
        Transaction saved = transactionService.transfer(userId, accountId, TransactionConverter.fromDto(transactionDto));
        URI createdUri = URI.create("/users" + userId + "/accounts/" + accountId + "/transactions");

        return Response.created(createdUri)
                .entity(saved)
                .build();
    }
}
