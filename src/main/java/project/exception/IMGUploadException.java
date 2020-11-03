package project.exception;

/**
 * Exception class in case of image uploading errors.
 */
public class IMGUploadException extends Throwable {
    public IMGUploadException(String message) {
        super(message);
    }
}
