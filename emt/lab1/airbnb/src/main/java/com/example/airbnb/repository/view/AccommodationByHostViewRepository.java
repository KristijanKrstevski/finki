package com.example.airbnb.repository.view;

import com.example.airbnb.model.views.AccommodationByHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
public interface AccommodationByHostViewRepository extends JpaRepository<AccommodationByHost,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.accommodations_by_host", nativeQuery = true)
    void refreshMaterializedView();

}
