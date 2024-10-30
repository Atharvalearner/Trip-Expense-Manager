package com.example.demo.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Expense;
import com.example.demo.repository.ExpenseRepository;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpensesByTrip(String tripId) {
        return expenseRepository.findAll(); // Implement filtering if needed
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(String id) {
        return expenseRepository.findById(id).orElse(null);
    }
    
    public List<Expense> getExpensesByTripId(String tripId) {
        return expenseRepository.findByTripId(tripId); // Custom method in repository
    }
    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}