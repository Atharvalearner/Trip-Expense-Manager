package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Expense {
    @Id
    private String id; // Primary key

    private String memberName;
    private String expenseDescription;
    private Double contributeAmount;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expense(String id, String memberName, String expenseDescription, Double contributeAmount, Trip trip) {
		super();
		this.id = id;
		this.memberName = memberName;
		this.expenseDescription = expenseDescription;
		this.contributeAmount = contributeAmount;
		this.trip = trip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public Double getContributeAmount() {
		return contributeAmount;
	}

	public void setContributeAmount(Double contributeAmount) {
		this.contributeAmount = contributeAmount;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

    
}
