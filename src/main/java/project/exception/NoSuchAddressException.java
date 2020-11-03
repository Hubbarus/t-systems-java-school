package project.exception;

/**
 * Exception class in case of no {@link project.entity.Address} entry in database.
 */
public class NoSuchAddressException extends RuntimeException {
    public NoSuchAddressException(String message) {
        super(message);
    }
}
