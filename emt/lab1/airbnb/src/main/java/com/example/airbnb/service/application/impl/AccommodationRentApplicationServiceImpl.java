package com.example.airbnb.service.application.impl;

import com.example.airbnb.dto.display.DisplayAccommodationDTO;
import com.example.airbnb.dto.display.DisplayHostDTO;
import com.example.airbnb.dto.display.DisplayUserDTO;
import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.domains.User;
import com.example.airbnb.service.application.AccommodationRentApplicationService;
import com.example.airbnb.service.domain.AccommodationRentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationRentApplicationServiceImpl implements AccommodationRentApplicationService {
    private final AccommodationRentService accommodationRentService;

    public AccommodationRentApplicationServiceImpl(AccommodationRentService accommodationRentService) {
        this.accommodationRentService = accommodationRentService;
    }

    @Override
    public List<DisplayAccommodationDTO> findAllRentedBooksForCurrentUser() {
        List<Accommodation> accommodations = accommodationRentService.findAllRentedAccommodationsForCurrentUser();
        return DisplayAccommodationDTO.from(accommodations);
    }

    @Override
    public Optional<DisplayAccommodationDTO> findMostRentedBook() {
        Accommodation accommodation = accommodationRentService.findMostRentedAccommodation();
        return Optional.of(DisplayAccommodationDTO.from(accommodation));
    }

    @Override
    public Optional<DisplayUserDTO> findUserWithMostRentedBooks() {
        User user = accommodationRentService.findUserWithMostRentedAccommodations();
        return Optional.of(DisplayUserDTO.from(user));
    }

    @Override
    public Optional<DisplayHostDTO> findMostRentedBookAuthor() {
        Host host = accommodationRentService.findHostOfMostRentedAccommodation();
        return Optional.of(DisplayHostDTO.from(host));
    }
}
