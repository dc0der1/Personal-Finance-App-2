package UI;

import models.Transaction;
import utility.DateUtility;


public class MonthlyTransactionsController extends TransactionsController {

    @Override
    public void load() {

        for (Transaction transactions : transactionService.getMonthly()) {

            defaultLoad(DateUtility.sqlDateToString(transactions.getDate()), String.valueOf(transactions.getAmount()));

        }
    }

}
