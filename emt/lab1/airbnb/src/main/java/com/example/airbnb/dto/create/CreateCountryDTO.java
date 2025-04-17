package com.example.airbnb.dto.create;

import com.example.airbnb.model.domains.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCountryDTO(
        String name,
        String continent
) {
     public Country toCountry()
     {
        return new Country(name,continent);
     }
     public static CreateCountryDTO from(Country country)
     {
         return new CreateCountryDTO(country.getName(),country.getContinent());
     }
     public static List<CreateCountryDTO> from(List<Country> countryList)
     {
         return countryList.stream()
                 .map(c->new CreateCountryDTO(c.getName(),c.getContinent()))
                 .toList();
     }
}
