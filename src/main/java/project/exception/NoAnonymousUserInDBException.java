package project.exception;

public class NoAnonymousUserInDBException extends RuntimeException {
    public NoAnonymousUserInDBException(String message) {
        super(message);
    }
}
