package com.budgettracker.budget_tracker_api;

public class TransactionRequest {
    private String description;
    private double amount;
    private String category;
    private int month;

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public int getMonth() {
        return month;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
