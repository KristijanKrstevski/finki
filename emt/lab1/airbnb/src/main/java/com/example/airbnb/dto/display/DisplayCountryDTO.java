package com.example.airbnb.dto.display;

import com.example.airbnb.dto.create.CreateCountryDTO;
import com.example.airbnb.model.domains.Country;

import java.util.List;

public record DisplayCountryDTO(
        Long id,
        String name,
        String continent
) {
    public Country toCountry()
    {
        return new Country(name,continent);
    }
    public static DisplayCountryDTO from(Country country)
    {
        return new DisplayCountryDTO(country.getId(), country.getName(),country.getContinent());
    }
    public static List<DisplayCountryDTO> from(List<Country> countryList)
    {
        return countryList.stream()
                .map(c->new DisplayCountryDTO(c.getId(),c.getName(),c.getContinent()))
                .toList();
    }
}
