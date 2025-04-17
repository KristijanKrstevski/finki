package com.example.airbnb.service.domain.impl;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.AccommodationRent;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.domains.User;
import com.example.airbnb.repository.AccommodationRentRepository;
import com.example.airbnb.service.domain.AccommodationRentService;
import com.example.airbnb.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccommodationRentServiceImpl implements AccommodationRentService {
    private final UserService userService;
    private final AccommodationRentRepository accommodationRentRepository;

    public AccommodationRentServiceImpl(UserService userService, AccommodationRentRepository accommodationRentRepository) {
        this.userService = userService;
        this.accommodationRentRepository = accommodationRentRepository;
    }

    @Override
    public List<Accommodation> findAllRentedAccommodationsForCurrentUser() {
        User authUser = userService.getAuthenticatedUser();
        List<Accommodation> accommodations = accommodationRentRepository.findByUser(authUser)
                .stream().map(AccommodationRent::getAccommodation).toList();

        if (accommodations.isEmpty()) {
            throw new RuntimeException("No accommodations found.");
        }

        return accommodations;
    }

    @Override
    public Accommodation findMostRentedAccommodation() {
        List<AccommodationRent> rents = accommodationRentRepository.findAll();

        Map<Accommodation, Long> countMap = rents.stream()
                .collect(Collectors.groupingBy(AccommodationRent::getAccommodation, Collectors.counting()));

        return countMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Host findHostOfMostRentedAccommodation() {
        List<AccommodationRent> rents = accommodationRentRepository.findAll();

        Map<Host, Long> countMap = rents.stream()
                .collect(Collectors.groupingBy(
                        rent -> rent.getAccommodation().getHost(),
                        Collectors.counting()
                ));

        return countMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public User findUserWithMostRentedAccommodations() {
        List<AccommodationRent> rents = accommodationRentRepository.findAll();

        Map<User, Long> countMap = rents.stream()
                .collect(Collectors.groupingBy(AccommodationRent::getUser, Collectors.counting()));

        return countMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new);
    }
}
