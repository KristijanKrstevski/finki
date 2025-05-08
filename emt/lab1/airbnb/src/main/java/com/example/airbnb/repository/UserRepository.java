package com.example.airbnb.repository;

import com.example.airbnb.model.domains.User;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @NonNull
    @Override
    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"rentedAccommodation"}
    )
    List<User> findAll();
}
