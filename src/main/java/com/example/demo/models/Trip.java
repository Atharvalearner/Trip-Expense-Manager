package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Trip {
    @Id
    private String id; // Primary key
    private String tripName;
    private String tripDescription;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();

    // Default constructor
    public Trip() {
        super();
    }

    // Constructor with parameters
    public Trip(String id, String tripName, String tripDescription, List<Expense> expenses) {
        super();
        this.id = id;
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.expenses = expenses;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripName() { // Corrected getter
        return tripName;
    }

    public void setTripName(String tripName) { // Corrected setter
        this.tripName = tripName;
    }

    public String getTripDescription() { // Corrected getter
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) { // Corrected setter
        this.tripDescription = tripDescription;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    // Helper methods to manage the bidirectional relationship
    public void addExpense(Expense expense) {
        expenses.add(expense);
        expense.setTrip(this);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
        expense.setTrip(null);
    }
}
