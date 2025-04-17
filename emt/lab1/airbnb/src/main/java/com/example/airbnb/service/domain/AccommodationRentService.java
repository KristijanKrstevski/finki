package com.example.airbnb.service.domain;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.domains.User;

import java.util.List;

public interface AccommodationRentService {
    List<Accommodation> findAllRentedAccommodationsForCurrentUser();
    Accommodation findMostRentedAccommodation();
    Host findHostOfMostRentedAccommodation();
    User findUserWithMostRentedAccommodations();
}
