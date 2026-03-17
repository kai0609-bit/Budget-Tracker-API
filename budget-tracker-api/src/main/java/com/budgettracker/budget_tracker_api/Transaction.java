package com.budgettracker.budget_tracker_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private long id;
    private String description;
    private double amount;
    private String category;
    private int month;

    // Constructors
    public Transaction () {}

    Transaction (String description, double amount, String category, int month) {
        if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        this.description = description;
        this.amount = amount;
        this.category = category;
        this.month = month;
    }

    // not using month
    Transaction (String description, double amount, String category) {
        this(description, amount, category, 0);
    }


    // Getters
    public long getId() {return id; }
    public String getDescription() {return description; }
    public double getAmount() {return amount; }
    public String getCategory() {return category; }
    public int getMonth() {return month; }

    // Setters
    public void setDescription(String description) {this.description = description; }
    public void setAmount(double amount) {this.amount = amount; }
    public void setCategory(String category) {this.category = category; }
    public void setMonth(int month) {this.month = month; }
}