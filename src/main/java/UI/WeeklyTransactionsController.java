package UI;

import models.Transaction;
import utility.DateUtility;

public class WeeklyTransactionsController extends TransactionsController {

    @Override
    public void load() {

        for (Transaction transactions : transactionService.getWeekly()) {

            defaultLoad(DateUtility.sqlDateToString(transactions.getDate()), String.valueOf(transactions.getAmount()));

        }
    }
}
