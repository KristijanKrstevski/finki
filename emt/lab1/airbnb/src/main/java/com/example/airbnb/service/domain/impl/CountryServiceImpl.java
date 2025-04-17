package com.example.airbnb.service.domain.impl;

import com.example.airbnb.model.domains.Country;
import com.example.airbnb.repository.CountryRepository;
import com.example.airbnb.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country create(Country country) {
        Country newCountry = new Country(country.getName(), country.getContinent());
        return countryRepository.save(newCountry);
    }

    @Override
    public Country update(Long ID, Country country) throws Exception {
        Country existing = countryRepository.findById(ID).orElseThrow(Exception::new);

        existing.setName(country.getName());
        existing.setContinent(country.getContinent());

        return countryRepository.save(existing);
    }

    @Override
    public void delete(Long ID) {
        countryRepository.deleteById(ID);
    }
}
