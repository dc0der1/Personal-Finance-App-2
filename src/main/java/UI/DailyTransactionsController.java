package UI;

import database.PostgreSQLTransactionRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DailyTransactionsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox verticalBox = new VBox();
    @FXML
    private ChoiceBox<String> dailyTransactions;
    @FXML
    private ChoiceBox<String> choiceBox;

    private final PostgreSQLTransactionRepository transactionRepository = new PostgreSQLTransactionRepository();

    public DailyTransactionsController() throws SQLException {}

    private String[] choices = {"Show All", "Yearly", "Monthly", "Weekly", "Daily", "Sign Out"};

    public void loadDailyTransactions() {

        for (Transaction transactions : transactionRepository.getDailyTransactions()) {

            Label transactionDate = new Label();
            Label transactionAmount = new Label();

            Button deleteBtn = new Button("Delete");

            transactionDate.setText(DateUtility.sqlDateToString(transactions.getDate()));
            transactionAmount.setText(String.valueOf(transactions.getAmount()));

            HBox hBox = new HBox();

            deleteBtn.setOnAction((event) -> {

                verticalBox.getChildren().remove(hBox);

                String title  = transactions.getTitle();
                Date date = (Date) transactions.getDate();
                int amount = transactions.getAmount();

                transactionRepository.deleteTransaction(title, date, amount);

            });

            hBox.getChildren().add(transactionDate);
            hBox.getChildren().add(transactionAmount);
            hBox.getChildren().add(deleteBtn);
            verticalBox.getChildren().add(hBox);

            TransactionsController.styleLabels(transactionDate);
            TransactionsController.styleLabels(transactionAmount);
            TransactionsController.styleBoxes(hBox, verticalBox);
            TransactionsController.styleButtons(deleteBtn);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(choices);
        choiceBox.getSelectionModel().select(4);
        choiceBox.setOnAction(this::getChoice);
    }

    public void getChoice(ActionEvent event) {
        String choice = choiceBox.getValue();
    }

}
