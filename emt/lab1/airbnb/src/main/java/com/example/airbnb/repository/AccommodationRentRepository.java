package com.example.airbnb.repository;

import com.example.airbnb.model.domains.AccommodationRent;
import com.example.airbnb.model.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRentRepository extends JpaRepository<AccommodationRent, Long> {
    List<AccommodationRent> findByUser(User user);
}
