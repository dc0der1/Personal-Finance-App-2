package models;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {

    private String title;
    private Date date;
    private int amount;

    public Transaction(String title, Date date, int amount) {
        this.title = title;
        this.date = date;
        this.amount = amount;
    }

    public Transaction(Date date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public Transaction(int amount) {
        this.amount = amount;
    }

    public String getTitle() { return title; }

    public Date getDate() { return date; }

    public int getAmount() { return amount; }
}
