package com.example.airbnb.dto.create;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.enumerations.AccommodationCategory;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDTO(
        String name,
        AccommodationCategory category,
        Long hostID,
        int numOfRooms
) {
    public Accommodation toAccommodation(Host host)
    {
        return new Accommodation(name,category,host,numOfRooms);
    }

    public static CreateAccommodationDTO from(Accommodation accommodation) {
        return new CreateAccommodationDTO(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()

        );
    }
    public static List<CreateAccommodationDTO> from(List<Accommodation> products) {
        return products.stream()
                .map(CreateAccommodationDTO::from)
                .toList();
    }
}
