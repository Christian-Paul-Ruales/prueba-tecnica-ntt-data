package ec.nttdata.transaction_account_ms.domain.exception;

public class BadResultException extends RuntimeException {
    public BadResultException(String message) {
        super(message);
    }
}
