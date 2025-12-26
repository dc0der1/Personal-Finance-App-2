package service;

import models.Account;

public interface IAccountService {

    void sendAccountToDB(Account account) throws Exception;
    int getUserByID(String username) throws Exception;

}
