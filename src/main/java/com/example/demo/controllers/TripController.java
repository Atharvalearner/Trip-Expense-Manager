package com.example.demo.controllers;

//import java.math.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Expense;
import com.example.demo.models.Trip;
import com.example.demo.services.ExpenseService;
import com.example.demo.services.TripService;

@Controller
public class TripController {
    @Autowired
    private TripService tripService;

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/trips")
    public String getAllTrips(Model model) {
        List<Trip> trips = tripService.getAllTrips();
        model.addAttribute("trips", trips);
        return "tripList";
    }

    @GetMapping("/trip/new")
    public String showTripForm(Model model) {
        model.addAttribute("trip", new Trip());
        return "tripForm";
    }

    @PostMapping("/trip/save")
    public String saveTrip(@ModelAttribute("trip") Trip trip) {
        tripService.saveTrip(trip);
        return "redirect:/trips";
    }

    @GetMapping("/trip/edit/{id}")
    public String editTrip(@PathVariable String id, Model model) {
        Trip trip = tripService.getTripById(id);
        model.addAttribute("trip", trip);
        return "tripForm";
    }

    @GetMapping("/trip/delete/{id}")
    public String deleteTrip(@PathVariable String id) {
        tripService.deleteTrip(id);
        return "redirect:/trips";
    }

    @GetMapping("/trip/{tripId}/expenses")
    public String viewExpenses(@PathVariable String tripId, Model model) {
        Trip trip = tripService.getTripById(tripId);
        List<Expense> expenses = expenseService.getExpensesByTripId(tripId);
        // Calculate total amount using `contributeAmount`
        double totalAmount = expenses.stream().mapToDouble(Expense::getContributeAmount).sum();
        model.addAttribute("trip", trip);
        model.addAttribute("expenses", expenses);
        model.addAttribute("totalAmount", totalAmount);
        return "expenseList";
    }
    
    @GetMapping("/trip/{tripId}/expense/new")
    public String showExpenseForm(@PathVariable String tripId, Model model) {
        Expense expense = new Expense();
        expense.setTrip(tripService.getTripById(tripId));
        model.addAttribute("expense", expense);
        return "expenseForm";
    }

    @PostMapping("/trip/{tripId}/expense/save")
    public String saveExpense(@PathVariable String tripId, @ModelAttribute("expense") Expense expense) {
        expense.setTrip(tripService.getTripById(tripId));
        expenseService.saveExpense(expense);
        return "redirect:/trip/" + tripId + "/expenses";
    }

    @GetMapping("/trip/{tripId}/expense/edit/{expenseId}")
    public String editExpense(@PathVariable String tripId, @PathVariable String expenseId, Model model) {
        Expense expense = expenseService.getExpenseById(expenseId);
        model.addAttribute("expense", expense);
        return "expenseForm";
    }

    @GetMapping("/trip/{tripId}/expense/delete/{expenseId}")
    public String deleteExpense(@PathVariable String tripId, @PathVariable String expenseId) {
        expenseService.deleteExpense(expenseId);
        return "redirect:/trip/" + tripId + "/expenses";
    }
}