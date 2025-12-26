package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import models.Account;
import service.AccountService;

import java.sql.SQLException;

public class SignUpController {

    // Form Identifiers
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label errorLabel;

    // Service
    private final AccountService accountService = new AccountService();

    public SignUpController() throws SQLException {}

    public void signUpButtonOnAction(ActionEvent event) throws Exception {

        if (usernameField.getText().isBlank() || passwordField.getText().isBlank() || confirmPasswordField.getText().isBlank()) {
            errorLabel.setText("Please fill all the fields");
        } else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            errorLabel.setText("Passwords do not match");
        } else if (passwordField.getText().length() < 8) {
            errorLabel.setText("Password should be at least 8 characters");
        } else if (usernameField.getText().length() <= 3) {
            errorLabel.setText("Username should be at least 3 characters");
        } else {

            if (accountService.foundAccountByUsername(usernameField.getText())) {
                errorLabel.setText("Username is taken.");
            } else {
                Account account = new Account(usernameField.getText(), passwordField.getText());
                accountService.sendAccountToDB(account);
                errorLabel.setText("Account created successfully! Click login button.");
                errorLabel.setTextFill(Paint.valueOf("green"));
            }

        }

    }

}
