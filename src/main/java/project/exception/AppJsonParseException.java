package project.exception;

/**
 * Exception class in case of parsing error.
 */
public class AppJsonParseException extends Exception {
    public AppJsonParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
