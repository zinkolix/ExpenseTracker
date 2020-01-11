package com.olixstudios.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olixstudios.expense.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
