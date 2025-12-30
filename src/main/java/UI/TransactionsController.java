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
import service.ITransactionService;
import service.TransactionService;
import session.UserSession;
import utility.DateUtility;
import utility.SceneChangerUtility;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public abstract class TransactionsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    protected final ITransactionService transactionService = new TransactionService();

    @FXML
    private VBox verticalBox = new VBox();
    @FXML
    protected ChoiceBox<String> choiceBox;

    protected static final String[] choices = {"Show All", "Yearly", "Monthly", "Weekly", "Daily", "Balance", "Sign Out"};

    public abstract void load();

    public void getChoice(ActionEvent event) {
        String choice = choiceBox.getValue();
        SceneChangerUtility controller =  new SceneChangerUtility();
        controller.changeScene(choice, event);
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
}
