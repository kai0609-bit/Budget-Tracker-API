package com.budgettracker;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional; 
import java.util.stream.Collectors;

public class BudgetManager implements Searchable{
    private static int totalTransactions = 0;
    
    Map<String, Transaction> transactions = new HashMap<>();

    public static int getTotalTransactions() {
        return totalTransactions;
    }

    public static void resetTotalTransactions() {
        totalTransactions = 0;
    }

    void addTransaction(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);

        totalTransactions++;
    }

    public Optional<Transaction> findById(String id) {
        return Optional.ofNullable(transactions.get(id));
    }

    void printStats() {
        double totalAmount = transactions.values().stream()
            .filter(t -> t.getAmount() > 0)
            .mapToDouble(Transaction::getAmount)
            .sum();
        
        double totalExpenses = transactions.values().stream()
            .filter(t -> t.getAmount() < 0)
            .mapToDouble(Transaction::getAmount)
            .sum();

        double balance = totalAmount + totalExpenses;
        
        List<Transaction> expenses = transactions.values().stream()
            .filter(t -> t.getAmount() < 0)
            .toList();

        System.out.println("Total number of transactions: " + totalTransactions);
        System.out.println("Total income: " + totalAmount);
        System.out.println("Total expenses: " + totalExpenses);
        System.out.println("Balance: " + balance);

        expenses.forEach(t -> 
            System.out.println(t.getDescription() + ": " + t.getAmount() + " [" + t.getCategory() + "]")
        );
    }

    void monthlyStats(int month) {
        List<Transaction> monthlyTransactions =transactions.values().stream()
                .filter(t -> t.getMonth() == month)
                .collect(Collectors.toList());


        double totalAmount = monthlyTransactions.stream()
                .filter(t -> t.getAmount() > 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpenses = monthlyTransactions.stream()
                .filter(t -> t.getAmount() < 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = totalAmount + totalExpenses;

        List<Transaction> expenses = monthlyTransactions.stream()
                .filter(t -> t.getAmount() < 0)
                .toList();

        System.out.println("Total income: " + totalAmount);
        System.out.println("Total expenses: " + totalExpenses);
        System.out.println("Balance: " + balance);
    }

    void printAllTransaction() {
        transactions.values().forEach( t->
            System.out.println(t.getId() + " | " + t.getDescription() + " | £" + t.getAmount() + " | " + t.getCategory())
        );
    }

    void monthlyReport(int month) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        System.out.println("=== Monthly Report: " + months[month - 1] + " ===");
        Map<String, Double> report = transactions.values().stream()
                .filter(t -> t.getMonth() == month)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        report.forEach((category, total) ->
            System.out.printf("%-15s: £%.1f%n", category, total)
        );

        System.out.println("--------------------------");

        monthlyStats(month);
    }
}
