package com.example.airbnb.service.domain.impl;


import com.example.airbnb.dto.create.CreateAccommodationDTO;
import com.example.airbnb.dto.display.DisplayAccommodationDTO;
import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.exceptions.NoAvailableRoomsException;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.HostRepository;
import com.example.airbnb.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Accommodation create(Accommodation accommodation) throws Exception {
        Host host = this.hostRepository.findById(accommodation.getHost().getId()).orElseThrow(Exception::new);
        Accommodation accommodation1=new Accommodation(accommodation.getName(),accommodation.getCategory(),host,accommodation.getNumRooms());
        return this.accommodationRepository.save(accommodation1);

    }

    @Override
    public Accommodation update(Long id, Accommodation accommodation) throws Exception {
        Host host= this.hostRepository.findById(accommodation.getHost().getId()).orElseThrow(Exception::new);
        Accommodation accommodation1=this.accommodationRepository.findById(id).orElseThrow(Exception::new);

        accommodation.setName(accommodation.getName());
        accommodation.setCategory(accommodation.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodation.getNumRooms());
        return accommodationRepository.save(accommodation1);
    }

    @Override
    public void delete(Long id)  {
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

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

}
