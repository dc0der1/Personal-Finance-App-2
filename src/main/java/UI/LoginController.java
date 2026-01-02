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

    public LoginController() throws SQLException {}

    public void loginButtonOnAction(ActionEvent event) throws Exception {

        if (usernameTextField.getText().isBlank() || passwordPasswordField.getText().isBlank()) {
            errorLabel.setText("Please fill all the fields!");
            errorLabel.setTextFill(Paint.valueOf("RED"));
        } else {
            if (accountService.foundAccountByUsername(usernameTextField.getText())) {
                System.out.println("Account found!");

                UserSession.setUsername(usernameTextField.getText());
                UserSession.setId(accountService.getUserByID(usernameTextField.getText()));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Create.fxml"));
                root = loader.load();

                CreateTransactionController createTransactionController = loader.getController();
                createTransactionController.setWelcomeTitle(usernameTextField.getText());

                // Here we change the scene to Transaction scene/page
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                errorLabel.setText("Account not found!");
            }
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
