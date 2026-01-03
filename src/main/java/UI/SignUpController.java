package UI;

import at.favre.lib.crypto.bcrypt.BCrypt;
import exceptions.ValidationException;
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
        try {
            accountService.createAccount(usernameField.getText(), passwordField.getText(), confirmPasswordField.getText());
            errorLabel.setText("Account created successfully!");
            errorLabel.setTextFill(Paint.valueOf("green"));
        } catch (ValidationException e) {
            errorLabel.setText(e.getMessage());
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
