package exceptions;

public class UserAlreadyExistException extends CreateUserException {
    public UserAlreadyExistException(String username) {
        super("User with name " + username + " already exists!");
    }
}
