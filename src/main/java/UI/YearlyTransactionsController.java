package UI;

import models.Transaction;
import utility.DateUtility;

public class YearlyTransactionsController extends TransactionsController {

    @Override
    public void load() {
        for (Transaction transactions : transactionService.getYearly()) {
            defaultLoad(DateUtility.sqlDateToString(transactions.getDate()), String.valueOf(transactions.getAmount()));
        }
    }

}
