package UI;

import exceptions.ValidationException;
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
import service.AccountService;
import session.UserSession;

import java.sql.SQLException;
import java.util.Objects;

public class LoginController {

    private final AccountService accountService = new AccountService();

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

    public void loginButtonOnAction(ActionEvent event) throws Exception {

        try {
            accountService.authenticate(usernameTextField.getText(), passwordPasswordField.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Create.fxml"));
            root = loader.load();

            CreateTransactionController createTransactionController = loader.getController();
            createTransactionController.setWelcomeTitle(usernameTextField.getText());

            // Here we change the scene to Transaction scene/page
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (ValidationException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void signUpLinkOnAction(ActionEvent event) throws Exception {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/SignUpForm.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
