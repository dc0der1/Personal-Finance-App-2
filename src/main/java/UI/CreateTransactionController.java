package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import models.Transaction;
import service.AccountService;
import service.IAccountService;
import service.ITransactionService;
import service.TransactionService;
import session.UserSession;
import utility.DateUtility;

import java.io.IOException;
import java.sql.SQLException;

public class CreateTransactionController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label errorLabel;
    @FXML
    private Button createTransactionBtn;
    @FXML
    private Label titleLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private TextField titleField;
    @FXML
    private TextField amountField;
    @FXML
    private DatePicker dateField;
    @FXML
    private Label welcomeTitle;

    private final ITransactionService transactionService = new TransactionService();

    public CreateTransactionController() throws SQLException {}

    public void createButtonOnAction(ActionEvent event) {

        if (titleField.getText().isBlank() || amountField.getText().isBlank() || dateField.getValue() == null) {
            errorLabel.setText("Please fill all the fields");
        } else {

            if (!amountField.getText().matches("\\d*")) {
                errorLabel.setText("Please enter a valid amount");
            } else if (titleField.getText().matches("\\d*")) {
                errorLabel.setText("Please enter a valid title");
            } else {
                int amount = Integer.parseInt(amountField.getText());

                Transaction transaction = new Transaction(titleField.getText(), DateUtility.localdateToDate(dateField.getValue()), amount);
                transactionService.sendTransactionToDb(transaction);

                errorLabel.setText("A transaction is created");
                errorLabel.setTextFill(Paint.valueOf("GREEN"));

                titleField.clear();
                amountField.clear();
                dateField.setValue(null);
            }
        }
    }

    public void transactionsButtonOnAction(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Transactions.fxml"));
        root = loader.load();

        // Here we change the scene to Transaction scene/page
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Set welcome title based on username
    public void setWelcomeTitle(String username) {
        welcomeTitle.setText("Welcome " + username);
    }
}
