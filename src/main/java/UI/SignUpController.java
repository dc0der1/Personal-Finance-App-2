package UI;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import models.Account;
import service.AccountService;

import java.io.IOException;
import java.util.Objects;

public class SignUpController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Form Identifiers
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button exitButton;

    // Service
    private final AccountService accountService = new AccountService();

    public void signUpButtonOnAction(ActionEvent event) {

        if (usernameField.getText().isBlank() || passwordField.getText().isBlank() || confirmPasswordField.getText().isBlank()) {
            errorLabel.setText("Please fill all the fields");
        } else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            errorLabel.setText("Passwords do not match");
        } else if (passwordField.getText().length() < 8) {
            errorLabel.setText("Password should be at least 8 characters");
        } else if (usernameField.getText().length() < 3) {
            errorLabel.setText("Username should be at least 3 characters");
        } else {

            if (accountService.foundAccountByUsername(usernameField.getText())) {
                errorLabel.setText("Username is taken.");
            } else {
                String hashedPassword = BCrypt.withDefaults().hashToString(12, passwordField.getText().toCharArray());

                Account account = new Account(usernameField.getText(), hashedPassword);
                accountService.createAccount(account);
                errorLabel.setText("Account created successfully! Click login button.");
                errorLabel.setTextFill(Paint.valueOf("green"));
            }
        }
    }

    public void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void toLoginButtonOnAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Sign Out.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
