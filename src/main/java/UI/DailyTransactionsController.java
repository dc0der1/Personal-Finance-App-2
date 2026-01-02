package UI;

import javafx.scene.layout.VBox;
import models.Transaction;
import utility.DateUtility;

public class DailyTransactionsController extends TransactionsController {

    @Override
    public void load() {

        for (Transaction transactions : transactionService.getDaily()) {

            defaultLoad(DateUtility.sqlDateToString(transactions.getDate()), String.valueOf(transactions.getAmount()));
        }
    }
}
