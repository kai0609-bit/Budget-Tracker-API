package com.budgettracker.budget_tracker_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TransactionController {

    private final BudgetService budgetService;

    public TransactionController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return budgetService.getAllTransactions();
    }

    @PostMapping("/add")
    public void addTask(@RequestParam String description, @RequestParam double amount, @RequestParam String category, @RequestParam int month) {
        budgetService.addTransaction(description, amount, category, month);
    }

    @GetMapping("/find")
    public Optional<Transaction> findById(@RequestParam long id) {
        return budgetService.findById(id);
    }

    @GetMapping("/stats")
    public Map<String, Double> getStatus() {
        return budgetService.getStatus();
    }

    @GetMapping("/stats/monthly")
    public Map<String, Double> getMonthlySummary(@RequestParam int month) {
        return budgetService.getMonthlySummary(month);
    }
}
