package com.budgettracker.budget_tracker_api;

import java.util.Optional;

public interface Searchable {
    Optional<Transaction> findById(String id);
}