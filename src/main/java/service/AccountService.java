package service;

import database.PostgreSQLAccountRepository;
import models.Account;

import java.sql.SQLException;

public class AccountService implements IAccountService {

    private final PostgreSQLAccountRepository accountRepository = new PostgreSQLAccountRepository();

    public AccountService() throws SQLException {}

    @Override
    public void sendAccountToDB(Account account) throws Exception {
        accountRepository.addUser(account);
    }

    public boolean foundAccountByUsername(String username) {
        return accountRepository.foundByUsername(username);
    }

    @Override
    public int getUserByID(String username) throws Exception {
        return accountRepository.findIdByUsername(username);
    }
}