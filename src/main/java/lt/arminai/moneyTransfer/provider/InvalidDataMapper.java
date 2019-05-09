package lt.arminai.moneyTransfer.provider;

import lt.arminai.moneyTransfer.dto.exception.DataIntegrationException;
import lt.arminai.moneyTransfer.dto.exception.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidDataMapper implements ExceptionMapper<DataIntegrationException> {
    @Override
    public Response toResponse(DataIntegrationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(Response.Status.BAD_REQUEST.getStatusCode(), exception.getMessage()))
                .build();
    }
}
