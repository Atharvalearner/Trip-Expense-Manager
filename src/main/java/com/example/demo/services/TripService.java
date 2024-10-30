package com.example.demo.services;

import java.math.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Member;
import com.example.demo.models.Trip;
import com.example.demo.repository.MemberRepo;
import com.example.demo.repository.TripRepo;

@Service
public class TripService {
    @Autowired
    private TripRepo tripRepository;

    @Autowired
    private MemberRepo memberRepository; // Autowire MemberRepo here

    // Save a trip
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    // Save a member
    public Member saveMember(Member member) {
        return memberRepository.save(member); // Save member to the repository
    }

    public Trip getTripById(String id) {
        return tripRepository.findById(id).orElse(null);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
    public void deleteTrip(String id) {
        tripRepository.deleteById(id);
    }

    @SuppressWarnings("deprecation")
	public Map<Member, BigDecimal> calculateDistribution(List<Member> members) {
        Map<Member, BigDecimal> balanceMap = new HashMap<>();
        for (Member member : members) {
            BigDecimal total = member.getTotalContribution();
            BigDecimal count = BigDecimal.valueOf(members.size());  // Example divisor
            BigDecimal balance;
            if (count.compareTo(BigDecimal.ZERO) != 0) {
                balance = total.divide(count, BigDecimal.ROUND_HALF_UP);  // Safe division
            } else {
                balance = BigDecimal.ZERO;  // Fallback value or handle as needed
            }

            balanceMap.put(member, balance);
        }

        return balanceMap;
    }
}

