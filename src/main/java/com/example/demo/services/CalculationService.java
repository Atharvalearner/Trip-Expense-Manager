package com.example.demo.services;

import com.example.demo.models.Expense;
import com.example.demo.models.Trip;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {
    public Map<String, Map<String, Double>> calculateExpenseShare(Trip trip) {
        List<Expense> expenses = trip.getExpenses();
        Map<String, Double> contributions = new HashMap<>();
        double totalContribution = 0;

        // Calculate each member's total contribution
        for (Expense expense : expenses) {
            String member = expense.getMemberName();
            contributions.put(member, contributions.getOrDefault(member, 0.0) + expense.getContributeAmount());
            totalContribution += expense.getContributeAmount();
        }

        // Calculate the fair share for each member
        int memberCount = contributions.size();
        double fairShare = totalContribution / memberCount;

        // Calculate who owes whom and how much
        Map<String, Map<String, Double>> result = new HashMap<>();
        for (String member : contributions.keySet()) {
            double balance = contributions.get(member) - fairShare;

            if (balance < 0) {
                // This member owes money
                Map<String, Double> owesTo = new HashMap<>();
                double amountOwed = -balance;

                for (String otherMember : contributions.keySet()) {
                    if (otherMember.equals(member)) continue;
                    double otherBalance = contributions.get(otherMember) - fairShare;

                    if (otherBalance > 0) {
                        double amountToPay = Math.min(amountOwed, otherBalance);
                        owesTo.put(otherMember, amountToPay);
                        amountOwed -= amountToPay;
                        contributions.put(otherMember, contributions.get(otherMember) - amountToPay);
                    }

                    if (amountOwed <= 0) break;
                }

                result.put(member, owesTo);
            }
        }

        return result;
    }
}
