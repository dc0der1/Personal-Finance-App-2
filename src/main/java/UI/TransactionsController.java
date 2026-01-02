package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.ITransactionService;
import service.TransactionService;
import utility.SceneChangerUtility;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class TransactionsController implements Initializable {

    protected final ITransactionService transactionService = new TransactionService();

    @FXML
    private VBox verticalBox = new VBox();
    @FXML
    protected ChoiceBox<String> choiceBox;

    protected static final String[] choices = {"Show All", "Yearly", "Monthly", "Weekly", "Daily", "Balance", "Create", "Sign Out"};

    public abstract void load();

    public void defaultLoad(String date, String amount) {
        Label transactionDate = new Label();
        Label transactionAmount = new Label();

        transactionDate.setText(date);
        transactionAmount.setText(amount);

        HBox hBox = new HBox();

        hBox.getChildren().add(transactionDate);
        hBox.getChildren().add(transactionAmount);

        verticalBox.getChildren().add(hBox);

        TransactionsController.styleLabels(transactionDate);
        TransactionsController.styleLabels(transactionAmount);
        TransactionsController.styleBoxes(hBox, verticalBox);
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
        choiceBox.getSelectionModel().getSelectedItem();
        choiceBox.setOnAction(this::getChoice);
    }

    public void getChoice(ActionEvent event) {
        String choice = choiceBox.getValue();
        SceneChangerUtility controller =  new SceneChangerUtility();
        controller.changeScene(choice, event);
    }
}
