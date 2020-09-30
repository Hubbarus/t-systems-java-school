package project.exception;

public class NoSuchAddressException extends RuntimeException {
    public NoSuchAddressException(String message) {
        super(message);
    }
}
