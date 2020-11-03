package project.exception;

/**
 * Exception class in case of no {@link project.entity.Item} entry in database.
 */
public class NoSuchItemException extends Throwable {
    public NoSuchItemException(String message) {
        super(message);
    }
}
