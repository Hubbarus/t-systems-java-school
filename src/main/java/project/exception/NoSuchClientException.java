package project.exception;

/**
 * Exception class in case of no {@link project.entity.Client} entry in database.
 */
public class NoSuchClientException extends Throwable {
    public NoSuchClientException(String message) {
        super(message);
    }
}
