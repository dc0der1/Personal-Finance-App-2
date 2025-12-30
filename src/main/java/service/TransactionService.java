package service;

import database.IAccountRepository;
import database.ITransactionRepository;
import database.PostgreSQLTransactionRepository;
import models.Transaction;
import utility.DateUtility;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TransactionService implements ITransactionService{

    private final ITransactionRepository transactionRepository = new PostgreSQLTransactionRepository();

    @Override
    public void sendTransactionToDb(Transaction transaction) {
        transactionRepository.addTransaction(transaction);
    }

    @Override
    public boolean foundSameTitle(String title) { return transactionRepository.isSameTitle(title); }

    @Override
    public List<Transaction> getAllTransactions() { return transactionRepository.getAllUserTransactions(); }

    @Override
    public List<Transaction> getDaily() { return transactionRepository.getDailyTransactions(); }

    @Override
    public List<Transaction> getWeekly() { return transactionRepository.getWeeklyTransactions(); }

    @Override
    public List<Transaction> getMonthly() { return transactionRepository.getMonthlyTransactions(); }

    @Override
    public List<Transaction> getYearly() { return transactionRepository.getYearlyTransactions(); }

    @Override
    public List<Transaction> getBalance() { return transactionRepository.getBalance(); }

    @Override
    public void deleteTransaction(String title, Date date, int amount) {
        transactionRepository.deleteTransaction(title, DateUtility.dateToSqlDate(date), amount);
    }

}
