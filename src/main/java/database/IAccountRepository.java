package database;

import models.Account;

public interface IAccountRepository {

    void addUser(Account account);
    int findIdByUsername(String username);

}
