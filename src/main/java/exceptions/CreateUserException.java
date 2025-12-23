package exceptions;

public class CreateUserException extends RuntimeException {
    public CreateUserException(String message) {
        super(message);
    }

    public CreateUserException(String message, Exception exception) {
        super(message, exception);
    }
}
