package com.example.airbnb.service.impl;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Available;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.dto.AvailabaleDTO;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.AvailableRepository;
import com.example.airbnb.service.AvailableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailableServiceImpl implements AvailableService {

    private final AvailableRepository availableRepository;
    private final AccommodationRepository accommodationRepository;

    public AvailableServiceImpl(AvailableRepository availableRepository, AccommodationRepository accommodationRepository) {
        this.availableRepository = availableRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Available> findAll() {
        return this.availableRepository.findAll();
    }

    @Override
    public Available create(AvailabaleDTO availabaleDTO) throws Exception {
        Optional<Accommodation> accommodationOptional = accommodationRepository.findById(availabaleDTO.getAccommodationId());


        Accommodation accommodation = accommodationOptional.get();

        Available available = new Available();
        available.setDate_from(availabaleDTO.getDate_from());
        available.setDate_to(availabaleDTO.getDate_to());
        available.setPrice(availabaleDTO.getPrice());
        available.setAccommodation(accommodation);

        return availableRepository.save(available);
    }

    @Override
    public Available update(Long id, AvailabaleDTO availabaleDTO) throws Exception {
        Optional<Available> availableOptional = availableRepository.findById(id);

        Available available = availableOptional.get();


        Optional<Accommodation> accommodationOptional = accommodationRepository.findById(availabaleDTO.getAccommodationId());

        Accommodation accommodation = accommodationOptional.get();

        available.setDate_from(availabaleDTO.getDate_from());
        available.setDate_to(availabaleDTO.getDate_to());
        available.setPrice(availabaleDTO.getPrice());
        available.setAccommodation(accommodation);

        return availableRepository.save(available);
    }

    @Override
    public void delete(Long id) {
        if (availableRepository.existsById(id)) {
            availableRepository.deleteById(id);
        }
    }
}
