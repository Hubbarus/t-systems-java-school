package project.exception;

/**
 * Exception class in case of no {@link project.entity.Item} category in database.
 */
public class NoSuchItemGroupException extends RuntimeException {
    public NoSuchItemGroupException(String message) {
        super(message);
    }
}
