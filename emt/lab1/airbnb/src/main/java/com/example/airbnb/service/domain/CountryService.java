package com.example.airbnb.service.domain;

import com.example.airbnb.model.domains.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Country create(Country country);

    Country update(Long ID, Country country) throws Exception;

    void delete(Long ID);


    //void refreshMaterializedView();
}
