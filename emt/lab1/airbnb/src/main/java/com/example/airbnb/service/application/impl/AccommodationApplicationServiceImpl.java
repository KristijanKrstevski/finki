package com.example.airbnb.service.application.impl;


import com.example.airbnb.dto.create.CreateAccommodationDTO;
import com.example.airbnb.dto.display.DisplayAccommodationDTO;
import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.views.AccommodationByHost;
import com.example.airbnb.repository.HostRepository;
import com.example.airbnb.repository.view.AccommodationByHostViewRepository;
import com.example.airbnb.service.application.AccommodationApplicationService;

import com.example.airbnb.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostRepository hostRepository;

    private final AccommodationByHostViewRepository accommodationByHostViewRepository;
    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostRepository hostRepository, AccommodationByHostViewRepository accommodationByHostViewRepository) {
        this.accommodationService = accommodationService;
        this.hostRepository = hostRepository;
        this.accommodationByHostViewRepository = accommodationByHostViewRepository;
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
    public void addToTemporarilyList(Long id,String token) {
        accommodationService.addToTemporarilyList(id,token);
    }

    @Override
    public void removeFromTemporarilyList(Long id,String token) {
        accommodationService.removeFromTemporarilyList(id,token);
    }

    @Override
    public List<DisplayAccommodationDTO> findAllFromTemporarilyList(String token) {
        return accommodationService.findAllFromTemporarilyList(token).stream()
                .map(DisplayAccommodationDTO::from).toList();
    }

    @Override
    public boolean rentByAccommodationById(Long id,String token) {
        return accommodationService.rentByAccommodationById(id,token);
    }

    @Override
    public void rentAllFromTemporarilyList(String token) {
        accommodationService.rentAllFromTemporarilyList(token);
    }

    @Override
    public List<AccommodationByHost> getAccommodationByHost() {
        return accommodationByHostViewRepository.findAll();
    }


}
