package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Transaction;
import service.ITransactionService;
import service.TransactionService;
import utility.DateUtility;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowAllController extends TransactionsController {

    @FXML
    private VBox verticalBox = new VBox();

    @Override
    public void load() {
        for (Transaction transactions : transactionService.getAllTransactions()) {

            Label transactionTitle = new Label();
            Label transactionDate = new Label();
            Label transactionAmount = new Label();

            Button deleteBtn = new Button("Delete");

            transactionTitle.setText(transactions.getTitle());
            transactionDate.setText(DateUtility.sqlDateToString(transactions.getDate()));
            transactionAmount.setText(String.valueOf(transactions.getAmount()));

            HBox hBox = new HBox();

            deleteBtn.setOnAction((event) -> {

                verticalBox.getChildren().remove(hBox);

                String title  = transactions.getTitle();
                Date date = (Date) transactions.getDate();
                int amount = transactions.getAmount();

                transactionService.deleteTransaction(title, date, amount);

            });

            hBox.getChildren().add(transactionTitle);
            hBox.getChildren().add(transactionDate);
            hBox.getChildren().add(transactionAmount);
            hBox.getChildren().add(deleteBtn);
            verticalBox.getChildren().add(hBox);

            styleLabels(transactionTitle);
            styleLabels(transactionDate);
            styleLabels(transactionAmount);
            styleBoxes(hBox, verticalBox);
            styleButtons(deleteBtn);

        }
    }

}
