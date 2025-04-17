package com.example.airbnb.service.application.impl;


import com.example.airbnb.dto.create.CreateAccommodationDTO;
import com.example.airbnb.dto.display.DisplayAccommodationDTO;
import com.example.airbnb.dto.display.DisplayHostDTO;
import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.exceptions.NoAvailableRoomsException;
import com.example.airbnb.repository.HostRepository;
import com.example.airbnb.service.application.AccommodationApplicationService;

import com.example.airbnb.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostRepository hostRepository;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostRepository hostRepository) {
        this.accommodationService = accommodationService;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<DisplayAccommodationDTO> findAll() {
        return this.accommodationService.findAll().stream()
                .map(DisplayAccommodationDTO::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDTO> findById(long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDTO::from);
    }

    @Override
    public Accommodation create(CreateAccommodationDTO accommodationDTO) throws Exception {
        Host host = this.hostRepository.findById(accommodationDTO.hostID()).orElseThrow(Exception::new);
        Accommodation accommodation=new Accommodation(accommodationDTO.name(),accommodationDTO.category(),host,accommodationDTO.numOfRooms());
        return this.accommodationService.create(accommodation);

    }

    @Override
    public Accommodation update(Long id, CreateAccommodationDTO accommodationDTO) throws Exception {
        Host host = this.hostRepository.findById(accommodationDTO.hostID()).orElseThrow(Exception::new);
        Accommodation accommodation=this.accommodationService.findById(id).orElseThrow(Exception::new);

        accommodation.setName(accommodationDTO.name());
        accommodation.setCategory(accommodationDTO.category());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDTO.numOfRooms());
        return accommodationService.create(accommodation);
    }

    @Override
    public void delete(Long id)  {
        this.accommodationService.delete(id);
    }

    @Override
    public Accommodation reservation(Long id) throws Exception {
        Accommodation accommodation=this.accommodationService.findById(id).orElseThrow(Exception::new);

        if (accommodation.getNumRooms() == 0) {
            throw new NoAvailableRoomsException(id);
        }

        accommodation.setNumRooms(accommodation.getNumRooms() - 1);
        return this.accommodationService.create(accommodation);
    }

}
