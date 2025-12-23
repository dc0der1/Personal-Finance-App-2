package UI;

import database.PostgreSQLAccountRepository;
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
import models.Account;
import service.AccountService;

import java.sql.SQLException;
import java.util.Objects;

public class LoginController {

    private final AccountService accountService = new AccountService();
    private final PostgreSQLAccountRepository postgreSQLAccountRepository = new PostgreSQLAccountRepository();

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

//            Account account = new Account(usernameTextField.getText(), passwordPasswordField.getText());
//            accountService.sendAccountToDB(account);

            // Here we change the scene to Transaction scene/page
//            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Transaction.fxml")));
//            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
            postgreSQLAccountRepository.findUserByUsername(usernameTextField.getText(), passwordPasswordField.getText());
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
