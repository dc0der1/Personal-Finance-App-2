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

public class BalanceController extends TransactionsController {

    @FXML
    private VBox verticalBox = new VBox();

    @Override
    public void load() {

        for (Transaction transactions : transactionService.getBalance()) {

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
}
