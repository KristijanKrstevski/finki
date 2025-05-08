package com.example.airbnb.repository;


import com.example.airbnb.model.domains.Host;
import com.example.airbnb.projection.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {

    @Query("select h.name as name, h.surname as surname from Host h")
    List<HostProjection> takeNameAndSurnameByProjection();

}
