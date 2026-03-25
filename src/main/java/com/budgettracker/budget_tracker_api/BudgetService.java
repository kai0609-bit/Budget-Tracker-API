package com.budgettracker.budget_tracker_api;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService (BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<Transaction> getAllTransactions() {
        return budgetRepository.findAll();
    }

    public Transaction addTransaction(String description, double amount, String category, int month) {
        Transaction transaction = new Transaction(description, amount, category, month);
        return budgetRepository.save(transaction);
    }

    public void removeTransaction(long id) {
        if (!budgetRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found");
        }
        budgetRepository.deleteById(id);
    }

    public Transaction updateTransaction(long id, TransactionRequest request) {
        Transaction transaction = budgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setCategory(request.getCategory());
        transaction.setMonth(request.getMonth());

        return budgetRepository.save(transaction);
    }

    public Optional<Transaction> findById(long id) {return budgetRepository.findById(id); }

    public Map<String, Double> getStatus() {
        List<Transaction> transactions = getAllTransactions();

        double totalAmount = transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpenses = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = totalAmount + totalExpenses;

        Map<String, Double> stats = new HashMap<>();
        stats.put("Total income", totalAmount);
        stats.put("Total expenses", totalExpenses);
        stats.put("Your balance", balance);

        return stats;
    }

    public Map<String, Double> getMonthlySummary(int month) {
        List<Transaction> monthlyTransactions = getAllTransactions().stream()
                .filter(t -> t.getMonth() == month)
                .toList();

        double totalAmount = monthlyTransactions.stream()
                .filter(t -> t.getAmount() > 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpenses = monthlyTransactions.stream()
                .filter(t -> t.getAmount() < 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = totalAmount + totalExpenses;

        Map<String, Double> monthlyStats = new HashMap<>();
        monthlyStats.put("Total income", totalAmount);
        monthlyStats.put("Total expenses", totalExpenses);
        monthlyStats.put("Your balance", balance);

        return monthlyStats;
    }
}
