package service;

import models.Transaction;

import java.util.Date;
import java.util.List;

public interface ITransactionService {

    void sendTransactionToDb(Transaction transaction);
    boolean foundSameTitle(String title);
    List<Transaction> getAllTransactions();
    List<Transaction> getDaily();
    List<Transaction> getWeekly();
    List<Transaction> getMonthly();
    List<Transaction> getYearly();
    List<Transaction> getBalance();
    void deleteTransaction(String title, Date date,int amount);

}
