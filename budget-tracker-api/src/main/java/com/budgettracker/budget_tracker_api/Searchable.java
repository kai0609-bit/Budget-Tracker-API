package com.budgettracker;

import java.util.Optional;

public interface Searchable {
    Optional<Transaction> findById(String id);
}