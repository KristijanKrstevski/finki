package com.example.airbnb.dto.display;

import com.example.airbnb.dto.create.CreateAccommodationDTO;
import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.enumerations.AccommodationCategory;

import java.util.List;

public record DisplayAccommodationDTO(
        Long id,
        String name,
        AccommodationCategory category,
        Long hostID,
        int numOfRooms
) {
    public Accommodation toAccommodation(Host host)
    {
        return new Accommodation(name,category,host,numOfRooms);
    }

    public static DisplayAccommodationDTO from(Accommodation accommodation) {
        return new DisplayAccommodationDTO(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()

        );
    }
    public static List<DisplayAccommodationDTO> from(List<Accommodation> products) {
        return products.stream()
                .map(DisplayAccommodationDTO::from)
                .toList();
    }
}
