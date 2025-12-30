package database;

import models.Transaction;

import java.sql.Date;
import java.util.List;

public interface ITransactionRepository {

    void addTransaction(Transaction transaction);
    boolean isSameTitle(String title);
    List<Transaction> getAllUserTransactions();
    void deleteTransaction(String title, Date date, int amount);
    List<Transaction> getDailyTransactions();
    List<Transaction> getWeeklyTransactions();
    List<Transaction> getMonthlyTransactions();
    List<Transaction> getYearlyTransactions();
    List<Transaction> getBalance();

}
