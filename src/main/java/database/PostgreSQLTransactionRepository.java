package database;

import models.Transaction;
import session.UserSession;
import utility.DateUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLTransactionRepository implements ITransactionRepository{

    private final Connection connection;

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/financedb";
    private static final String USER = "postgres";
    private static final String PASS = "password";

    public PostgreSQLTransactionRepository() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, USER, PASS);

        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS transactions (" +
                    "id SERIAL PRIMARY KEY, " +
                    "title VARCHAR(255) UNIQUE, " +
                    "date DATE, " +
                    "amount INT, " +
                    "user_id INT REFERENCES users(id))");
        }
    }

    @Override
    public void addTransaction(Transaction transaction) {

        String query = "INSERT INTO transactions (title, date, amount, user_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, transaction.getTitle());
            statement.setDate(2, DateUtility.dateToSqlDate(transaction.getDate()));
            statement.setInt(3, transaction.getAmount());
            statement.setInt(4, UserSession.getId());
            statement.executeUpdate();

            System.out.println("Database: Transaction added successfully");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public boolean isSameTitle(String title) {
        String query = "SELECT * FROM transactions WHERE title = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);

            ResultSet set = statement.executeQuery();

            if (!set.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return true;
    }

    public List<Transaction> getAllUserTransactions() {
        String query = "SELECT * FROM transactions INNER JOIN users ON transactions.user_id = users.id WHERE users.username = ?";
        List<Transaction> transactions = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, UserSession.getUsername());

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Transaction transaction = new Transaction(set.getString("title"), set.getDate("date"), set.getInt("amount"));
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return transactions;
    }

    public void deleteTransaction(String title, Date date, int amount) {
        String query = "DELETE FROM transactions WHERE transactions.title = ? AND transactions.date = ? AND transactions.amount = ? AND user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setDate(2, DateUtility.dateToSqlDate(date));
            statement.setInt(3, amount);
            statement.setInt(4, UserSession.getId());
            statement.executeUpdate();

            System.out.println("Database: Transaction deleted successfully");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public List<Transaction> getDailyTransactions() {
        String query = "SELECT date, SUM(amount) AS amount FROM transactions WHERE user_id = ? GROUP BY date ORDER BY date";
        List<Transaction> transactions = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, UserSession.getId());

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Transaction transaction = new Transaction(set.getDate("date"), set.getInt("amount"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return transactions;
    }

    public List<Transaction> getWeeklyTransactions() {
        String query = "SELECT trunc_date('weekly', date)::date AS date, SUM(amount) AS amount FROM transactions WHERE user_id = ? GROUP BY date ORDER BY date";
        List<Transaction> transactions = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, UserSession.getId());

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Transaction transaction = new Transaction(set.getDate("date"), set.getInt("amount"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return transactions;
    }
}
