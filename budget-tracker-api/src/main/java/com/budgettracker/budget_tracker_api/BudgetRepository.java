package com.budgettracker.budget_tracker_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BudgetRepository extends JpaRepository<Transaction, Long> {
}