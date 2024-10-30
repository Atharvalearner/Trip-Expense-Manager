package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Trip;

public interface TripRepo extends JpaRepository<Trip, String> { }
