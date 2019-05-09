package lt.arminai.moneyTransfer.provider;

import lt.arminai.moneyTransfer.dto.exception.ErrorMessage;
import lt.arminai.moneyTransfer.dto.exception.NotEnoughBalanceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotEnoughBalanceMapper implements ExceptionMapper<NotEnoughBalanceException> {
    @Override
    public Response toResponse(NotEnoughBalanceException exception) {
        return Response.status(Response.Status.PRECONDITION_FAILED)
                .entity(new ErrorMessage(Response.Status.PRECONDITION_FAILED.getStatusCode(), exception.getMessage()))
                .build();
    }
}
