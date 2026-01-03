package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import database.PostgreSQLAccountRepository;
import exceptions.ValidationException;
import models.Account;
import session.UserSession;

public class AccountService implements IAccountService {

    private final PostgreSQLAccountRepository accountRepository = new PostgreSQLAccountRepository();

    @Override
    public void createAccount(String username, String password, String confirmPassword) {
        if (username.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            throw new ValidationException("Username and password fields cannot be empty");
        } else if (!password.equals(confirmPassword)) {
            throw new ValidationException("Passwords do not match");
        } else if (password.length() < 8) {
            throw new ValidationException("Password should at least 8 characters");
        } else if (username.length() < 3) {
            throw new ValidationException("Username should at least 3 characters");
        } else if (username.length() > 32) {
            throw new ValidationException("Username should at most 32 characters");
        } else if (accountRepository.foundByUsername(username)) {
            throw new ValidationException("Username already exists");
        } else {
            Account account = new Account(username, BCrypt.withDefaults().hashToString(12, password.toCharArray()));
            accountRepository.addUser(account);
        }
    }

    public void authenticate(String username, String password) {

        BCrypt.Result result = BCrypt.verifyer().verify(password.getBytes(), accountRepository.getPasswordByUsername(username).getBytes());

        if (username.isBlank() || password.isBlank()) {
            throw new ValidationException("Username and password fields cannot be empty");
        } else if (!accountRepository.foundByUsername(username)) {
            throw new ValidationException("Username does not exist");
        } else if (!result.verified) {
            throw new ValidationException("Incorrect password");
        } else {
            System.out.println("Account found: " + username);
            UserSession.setUsername(username);
            UserSession.setId(getIdByUsername(username));
        }
    }

    public boolean foundAccountByUsername(String username) {
        return accountRepository.foundByUsername(username);
    }

    @Override
    public int getIdByUsername(String username) {
        return accountRepository.findIdByUsername(username);
    }
}