package database;

import models.Transaction;

public interface ITransactionRepository {

    void addTransaction(Transaction transaction);

}
