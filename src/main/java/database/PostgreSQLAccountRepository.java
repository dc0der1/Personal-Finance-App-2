package database;

import models.Account;

import java.sql.*;

public class PostgreSQLAccountRepository implements IAccountRepository {

    private Connection connection;

    private static final String db_URL = System.getenv("DB_URL");
    private static final String user = System.getenv("DB_USER");
    private static final String pass = System.getenv("DB_PASS");

    public PostgreSQLAccountRepository() {

        try {
            connection = DriverManager.getConnection(db_URL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        try {
            assert connection != null;
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                        "id SERIAL PRIMARY KEY, " +
                        "username VARCHAR(20) UNIQUE, " +
                        "password VARCHAR(100))");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void addUser(Account account) {

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.executeUpdate();
            System.out.println("Database: User added successfully");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public boolean foundByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();

            // If the user is not found
            if (!set.next()) {
                System.out.println("Database: User not found");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return true;
    }

    @Override
    public int findIdByUsername(String username) {
        String query = "SELECT id FROM users WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();

            if (!set.next()) {
                System.out.println("Database: User ID not found");
                return -1;
            }

            return set.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return -1;
    }
}
