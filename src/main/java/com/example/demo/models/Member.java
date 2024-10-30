package com.example.demo.models;

import java.math.*;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class Member {
	@Id
    private String id;
    private String name;
    private String description;
    private BigDecimal totalContribution;

    @Transient // Do not persist this field to the database
    private String tripId;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    // Getters and setters for tripId
    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
    
    
    public Member() {
        this.id = UUID.randomUUID().toString();
    }

    public Member(String name, String description, BigDecimal totalContribution, Trip trip) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.totalContribution = totalContribution;
        this.trip = trip;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getTotalContribution() {
		return totalContribution;
	}

	public void setTotalContribution(BigDecimal totalContribution) {
		this.totalContribution = totalContribution;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

}
