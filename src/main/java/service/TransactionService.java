package service;

import database.IAccountRepository;
import database.ITransactionRepository;
import database.PostgreSQLTransactionRepository;
import models.Transaction;

import java.sql.SQLException;

public class TransactionService implements ITransactionService{

    private final ITransactionRepository transactionRepository = new PostgreSQLTransactionRepository();

    public TransactionService() throws SQLException {}

    @Override
    public void sendTransactionToDb(Transaction transaction) {

        transactionRepository.addTransaction(transaction);

    }

}
