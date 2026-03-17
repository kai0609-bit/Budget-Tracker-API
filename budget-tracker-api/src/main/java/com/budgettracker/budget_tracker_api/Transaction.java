package com.budgettracker;

public class Transaction {
    // Properties
    private String id;
    private String description;
    private double amount;
    private String category;
    private int month;

    // Constructors
    Transaction (String id, String description, double amount, String category, int month) {
        if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.month = month;
    }

    // not using month
    Transaction (String id, String description, double amount, String category) {
        this(id, description, amount, category, 0);
    }


    // Getters
    public String getId() {return id; }
    public String getDescription() {return description; }
    public double getAmount() {return amount; }
    public String getCategory() {return category; }
    public int getMonth() {return month; }

    @Override
    public String toString() {
        return String.format("Transaction{id='%s', description='%s', amount='%f', category='%s', month='%d'}",
            id, description, amount, category, month);

    }
}