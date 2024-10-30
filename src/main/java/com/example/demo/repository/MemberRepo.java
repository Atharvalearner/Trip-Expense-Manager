package com.example.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Member;

public interface MemberRepo extends JpaRepository<Member, String> {
    List<Member> findByTrip_Id(String tripId); // Access the 'id' field of the 'trip' object
}

