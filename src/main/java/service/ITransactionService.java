package service;

import models.Transaction;

public interface ITransactionService {

    void sendTransactionToDb(Transaction transaction);

}
