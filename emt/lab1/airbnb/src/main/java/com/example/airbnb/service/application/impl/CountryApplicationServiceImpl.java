package com.example.airbnb.service.application.impl;

import com.example.airbnb.dto.create.CreateCountryDTO;
import com.example.airbnb.dto.display.DisplayCountryDTO;
import com.example.airbnb.model.domains.Country;
import com.example.airbnb.service.application.CountryApplicationService;
import com.example.airbnb.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }


    @Override
    public List<DisplayCountryDTO> findAll() {
        return countryService.findAll().stream()
                .map(DisplayCountryDTO::from)
                .toList();
    }

    @Override
    public Optional<DisplayCountryDTO> findById(long id) {
        return countryService.findById(id).map(DisplayCountryDTO::from);
    }

    @Override
    public Country create(CreateCountryDTO countryDTO) {
        Country newCountry = new Country(countryDTO.name(), countryDTO.continent());
        return countryService.create(newCountry);
    }

    @Override
    public Country update(Long id, CreateCountryDTO countryDTO) throws Exception {
        Country existing = countryService.findById(id)
                .orElseThrow(Exception::new);

        existing.setName(countryDTO.name());
        existing.setContinent(countryDTO.continent());

        return countryService.update(id, existing);
    }

    @Override
    public void delete(Long ID) {

    }
}
