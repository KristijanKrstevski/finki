package com.example.airbnb.repository.view;

import com.example.airbnb.model.views.HostByCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
public interface HostByCountryViewRepository extends JpaRepository<HostByCountry, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.hosts_by_country", nativeQuery = true)
    void refreshMaterializedView();

    HostByCountry findByCountryId(Long countryId);

}
