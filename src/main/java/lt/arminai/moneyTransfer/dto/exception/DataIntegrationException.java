package lt.arminai.moneyTransfer.dto.exception;

public class DataIntegrationException extends RuntimeException {
    public DataIntegrationException(String message) {
        super(message);
    }
}
