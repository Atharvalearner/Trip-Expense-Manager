package com.example.demo.services;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Member;
import com.example.demo.repository.MemberRepo;


@Service
public class MemberService {
	@Autowired
    private MemberRepo memberRepository;

    // Save a member
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    // Retrieve all members associated with a specific trip ID
    public List<Member> getMembersByTripId(String tripId) {
        return memberRepository.findByTrip_Id(tripId); // Ensure MemberRepo has this method
    }
}
