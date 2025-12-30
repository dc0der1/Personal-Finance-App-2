package UI;

import database.PostgreSQLTransactionRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Transaction;
import utility.DateUtility;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BalanceController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String[] choices = {"Show All", "Yearly", "Monthly", "Weekly", "Daily", "Sign Out"};

    @FXML
    private VBox verticalBox = new VBox();
    @FXML
    private ChoiceBox<String> dailyTransactions;
    @FXML
    private ChoiceBox<String> choiceBox;

    private final PostgreSQLTransactionRepository transactionRepository = new PostgreSQLTransactionRepository();

    public BalanceController() throws SQLException {}

    public void loadBalance() {

        for (Transaction transactions : transactionRepository.getBalance()) {

            Label balanceLabel = new Label("Balance: ");
            Label balance = new Label();

            balance.setText(String.valueOf(transactions.getAmount()));

            HBox hBox = new HBox();

            hBox.getChildren().add(balanceLabel);
            hBox.getChildren().add(balance);
            verticalBox.getChildren().add(hBox);
            verticalBox.alignmentProperty().set(Pos.CENTER);

            TransactionsController.styleLabels(balanceLabel);
            TransactionsController.styleLabels(balance);
            TransactionsController.styleBoxes(hBox, verticalBox);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(choices);
        choiceBox.getSelectionModel().select(2);
        choiceBox.setOnAction(this::getChoice);
    }

    public void getChoice(ActionEvent event) {
        String choice = choiceBox.getValue();
    }

}
