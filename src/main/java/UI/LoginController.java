package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button cancelButton;
    @FXML
    private Label errorLabel;

    public void loginButtonOnAction(ActionEvent event) throws IOException {

        if (usernameTextField.getText().isBlank() || passwordPasswordField.getText().isBlank()) {
            errorLabel.setText("Please fill all the fields!");
            errorLabel.setTextFill(Paint.valueOf("RED"));
        } else {
            // Here we change the scene to Transaction scene/page
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Transaction.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
