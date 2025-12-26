package models;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {

    private final String title;
    private final Date date;
    private final int amount;

    public Transaction(String title, Date date, int amount) {
        this.title = title;
        this.date = date;
        this.amount = amount;
    }

    public String getTitle() { return title; }

    public Date getDate() { return date; }

    public int getAmount() { return amount; }
}
