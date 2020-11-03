package project.exception;

/**
 * Exception class in case of queue errors.
 */
public class AppQueueException extends Exception {

    public AppQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}
