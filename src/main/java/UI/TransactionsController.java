package UI;

import database.PostgreSQLTransactionRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Transaction;
import session.UserSession;
import utility.DateUtility;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox verticalBox = new VBox();
    @FXML
    private ChoiceBox<String> choiceBox;

    private final PostgreSQLTransactionRepository transactionRepository = new PostgreSQLTransactionRepository();

    private String[] choices = {"Show All", "Yearly", "Monthly", "Weekly", "Daily", "Sign Out"};

    public TransactionsController() throws SQLException {}

    public void loadTransactions() {

        for (Transaction transactions : transactionRepository.getAllUserTransactions()) {

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

                transactionRepository.deleteTransaction(title, date, amount);

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

    public void getChoice(ActionEvent event) {
        String choice = choiceBox.getValue();
        if (choice.equals("Weekly")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/WeeklyTransactions.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }

            // Here we change the scene to Transaction scene/page
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            WeeklyTransactionsController controller = loader.getController();
            controller.loadDailyTransactions();
        }
    }

    // Style method
    public static void styleLabels(Label label) {

        label.setStyle(
                "-fx-border-color: black;" +
                "-fx-pref-width: 150px;" +
                "-fx-font-size: 16px;" +
                "-fx-alignment: center;"
        );

    }

    public static void styleButtons(Button button) {

        button.setStyle(
                "-fx-border-radius: 5px;" +
                "-fx-background-color: red;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;" +
                "-fx-border-color: black;" +
                "-fx-cursor: hand;"
        );

    }

    public static void styleBoxes(HBox hBox, VBox vBox) {

        hBox.alignmentProperty().set(Pos.CENTER);

        vBox.setStyle("-fx-spacing: 10px");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(choices);
        choiceBox.getSelectionModel().select(0);
        choiceBox.setOnAction(this::getChoice);
    }
}
