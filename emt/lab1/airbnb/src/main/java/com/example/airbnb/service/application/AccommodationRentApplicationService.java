package com.example.airbnb.service.application;

import com.example.airbnb.dto.display.DisplayAccommodationDTO;
import com.example.airbnb.dto.display.DisplayHostDTO;
import com.example.airbnb.dto.display.DisplayUserDTO;

import java.util.List;
import java.util.Optional;

public interface AccommodationRentApplicationService {
    List<DisplayAccommodationDTO> findAllRentedBooksForCurrentUser();
    Optional<DisplayAccommodationDTO> findMostRentedBook();
    Optional<DisplayUserDTO> findUserWithMostRentedBooks();
    Optional<DisplayHostDTO> findMostRentedBookAuthor();
}
