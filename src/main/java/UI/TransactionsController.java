package UI;

import database.PostgreSQLTransactionRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Transaction;
import utility.DateUtility;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TransactionsController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox vboxTest;

    private final PostgreSQLTransactionRepository transactionRepository = new PostgreSQLTransactionRepository();

    public TransactionsController() throws SQLException {}



    public void testOnAction(ActionEvent event) {

        for (Transaction transactions : transactionRepository.getTitleTransaction()) {

            Label transactionTitle = new Label();
            Label transactionDate = new Label();
            Label transactionAmount = new Label();

            transactionTitle.setText(transactions.getTitle());
            transactionDate.setText(DateUtility.sqlDateToString(transactions.getDate()));
            transactionAmount.setText(String.valueOf(transactions.getAmount()));

            HBox hBox = new HBox();

            hBox.getChildren().add(transactionTitle);
            hBox.getChildren().add(transactionDate);
            hBox.getChildren().add(transactionAmount);
            vboxTest.getChildren().add(hBox);

            // Style


        }
    }

}
