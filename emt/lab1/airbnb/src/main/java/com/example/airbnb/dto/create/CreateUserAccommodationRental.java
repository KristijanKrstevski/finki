package com.example.airbnb.dto.create;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.AccommodationRent;
import com.example.airbnb.model.domains.User;

public record CreateUserAccommodationRental(
        Long userId,
        Long accommodationId
) {
    public AccommodationRent toUserAccommodationRental(User user, Accommodation accommodation) {
        return new AccommodationRent(user, accommodation);
    }
}
