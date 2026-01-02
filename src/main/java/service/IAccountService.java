package service;

import models.Account;

public interface IAccountService {

    void createAccount(Account account);
    int getUserByID(String username);

}
