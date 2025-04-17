package com.example.airbnb.service.application;

import com.example.airbnb.dto.create.CreateCountryDTO;
import com.example.airbnb.dto.display.DisplayCountryDTO;
import com.example.airbnb.model.domains.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDTO> findAll();
    Optional<DisplayCountryDTO> findById(long id);
    Country create(CreateCountryDTO countryDTO);

    Country update(Long ID, CreateCountryDTO countryDTO) throws Exception;

    void delete(Long ID);
}
