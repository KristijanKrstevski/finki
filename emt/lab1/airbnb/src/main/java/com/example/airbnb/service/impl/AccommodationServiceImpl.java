package com.example.airbnb.service.impl;



import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.dto.AccommodationDTO;
import com.example.airbnb.model.exceptions.NoAvailableRoomsException;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.HostRepository;
import com.example.airbnb.service.AccommodationService;
import com.example.airbnb.model.domains.Host;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Accommodation create(AccommodationDTO accommodationDTO) throws Exception {
        Host host = this.hostRepository.findById(accommodationDTO.getHostID()).orElseThrow(Exception::new);
        Accommodation accommodation=new Accommodation(accommodationDTO.getName(),accommodationDTO.getCategory(),host,accommodationDTO.getNumOfRooms());
        return this.accommodationRepository.save(accommodation);

    }

    @Override
    public Accommodation update(Long id, AccommodationDTO accommodationDTO) throws Exception {
        Host host= this.hostRepository.findById(accommodationDTO.getHostID()).orElseThrow(Exception::new);
        Accommodation accommodation=this.accommodationRepository.findById(id).orElseThrow(Exception::new);

        accommodation.setName(accommodationDTO.getName());
        accommodation.setCategory(accommodationDTO.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDTO.getNumOfRooms());
        return accommodationRepository.save(accommodation);
    }

    @Override
    public void delete(Long id) {
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public Accommodation reservation(Long id) throws Exception {
        Accommodation accommodation=this.accommodationRepository.findById(id).orElseThrow(Exception::new);

        if (accommodation.getNumRooms() == 0) {
            throw new NoAvailableRoomsException(id);
        }

        accommodation.setNumRooms(accommodation.getNumRooms() - 1);
        return this.accommodationRepository.save(accommodation);
    }

}
