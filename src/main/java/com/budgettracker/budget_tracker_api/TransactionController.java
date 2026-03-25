package com.budgettracker.budget_tracker_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionRequest request) {
        Transaction savedTransaction = budgetService.addTransaction(
                request.getDescription(),
                request.getAmount(),
                request.getCategory(),
                request.getMonth()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable long id) {
        budgetService.removeTransaction(id);
        return ResponseEntity.noContent().build();
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
