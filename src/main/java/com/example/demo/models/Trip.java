package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Trip {
    @Id
    private String id; 
    private String tripName;
    private String tripDescription;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();

    public Trip() {
        super();
    }

    public Trip(String id, String tripName, String tripDescription, List<Expense> expenses) {
        super();
        this.id = id;
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.expenses = expenses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripName() { 
        return tripName;
    }

    public void setTripName(String tripName) { 
        this.tripName = tripName;
    }

    public String getTripDescription() { 
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) { 
        this.tripDescription = tripDescription;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        expense.setTrip(this);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
        expense.setTrip(null);
    }
}
