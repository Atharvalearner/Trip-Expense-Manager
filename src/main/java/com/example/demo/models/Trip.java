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
    private String destination;
    private String startDate;
    private String endDate;
    private Double budget;

    public Trip(String id, String tripName, String tripDescription, String destination, String startDate,
			String endDate, Double budget, List<Expense> expenses) {
		super();
		this.id = id;
		this.tripName = tripName;
		this.tripDescription = tripDescription;
		this.destination = destination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.budget = budget;
		this.expenses = expenses;
	}

	@OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();

    public Trip() {
        super();
    }

    public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
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
