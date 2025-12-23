package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class TransactionController {

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

    public void createButtonOnAction(ActionEvent event) {

        if (titleField.getText().isBlank() || amountField.getText().isBlank() || dateField.getValue() == null) {
            errorLabel.setText("Please fill all the fields");
            errorLabel.setTextFill(Paint.valueOf("RED"));
        } else {
            errorLabel.setText("A transaction is created");
            errorLabel.setTextFill(Paint.valueOf("GREEN"));
        }

    }

}
