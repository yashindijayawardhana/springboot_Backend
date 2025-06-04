package com.example.finalcoursework_lostfound.repository;


import com.example.finalcoursework_lostfound.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
    // Custom query methods can be added here if needed
    // Example: List<Request> findByStatus(RequestStatus status);
}