package service;

import models.Account;

public interface IAccountService {

    void createAccount(String username, String password, String confirmPassword);
    int getIdByUsername(String username);

}
