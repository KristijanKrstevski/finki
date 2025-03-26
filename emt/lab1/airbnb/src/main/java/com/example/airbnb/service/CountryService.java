package com.example.airbnb.service;

import com.example.airbnb.model.domains.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
}
