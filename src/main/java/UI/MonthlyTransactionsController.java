package UI;

import database.PostgreSQLTransactionRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Transaction;
import utility.DateUtility;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MonthlyTransactionsController extends TransactionsController {

    @FXML
    private VBox verticalBox = new VBox();

    @Override
    public void load() {

        for (Transaction transactions : transactionService.getMonthly()) {

            Label transactionDate = new Label();
            Label transactionAmount = new Label();

            transactionDate.setText(DateUtility.sqlDateToString(transactions.getDate()));
            transactionAmount.setText(String.valueOf(transactions.getAmount()));

            HBox hBox = new HBox();

            hBox.getChildren().add(transactionDate);
            hBox.getChildren().add(transactionAmount);
            verticalBox.getChildren().add(hBox);

            TransactionsController.styleLabels(transactionDate);
            TransactionsController.styleLabels(transactionAmount);
            TransactionsController.styleBoxes(hBox, verticalBox);

        }
    }

}
