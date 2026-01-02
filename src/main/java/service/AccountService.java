package service;

import database.PostgreSQLAccountRepository;
import models.Account;

import java.sql.SQLException;

public class AccountService implements IAccountService {

    private final PostgreSQLAccountRepository accountRepository = new PostgreSQLAccountRepository();

    @Override
    public void createAccount(Account account) {
        accountRepository.addUser(account);
    }

    public boolean foundAccountByUsername(String username) {
        return accountRepository.foundByUsername(username);
    }

    @Override
    public int getUserByID(String username) {
        return accountRepository.findIdByUsername(username);
    }
}