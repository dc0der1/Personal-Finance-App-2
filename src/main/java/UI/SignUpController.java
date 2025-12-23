package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    public void signUpButtonOnAction(ActionEvent event) {

        if (usernameField.getText().isBlank() || passwordField.getText().isBlank() || confirmPasswordField.getText().isBlank()) {
            errorLabel.setText("Please fill all the fields");
        } else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            errorLabel.setText("Passwords do not match");
        } else {

        }

    }

}
