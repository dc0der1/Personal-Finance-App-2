package database;

import exceptions.UserAlreadyExistException;
import models.Account;

import java.sql.*;

public class PostgreSQLAccountRepository implements IAccountRepository {

    private final Connection connection;

    static final String JDBC_URL = "jdbc:postgresql://localhost:5432/financedb";
    private static final String USER = "postgres";
    private static final String PASS = "password";

    public PostgreSQLAccountRepository() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, USER, PASS);

        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "username VARCHAR(20) UNIQUE, " +
                    "password VARCHAR(100))");
        }
    }

    @Override
    public void addUser(Account account) {

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    // This method is for sign up controller
    // We create a new account if it doesn't exist
    public void findUserByUsername(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();

            // If the user is not found
            if (!set.next()) {
                addUser(new Account(username, password));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    // This method is for login controller
    public void findUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();

            // If the user is not found
            if (!set.next()) {

            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
