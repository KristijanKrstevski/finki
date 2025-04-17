package com.example.airbnb.service.application.impl;//package com.example.airbnb.service.impl;
//
//import com.example.airbnb.model.domains.Accommodation;
//import com.example.airbnb.model.domains.Available;
//import com.example.airbnb.dto.AvailabaleDTO;
//import com.example.airbnb.repository.AccommodationRepository;
//import com.example.airbnb.repository.AvailableRepository;
//import com.example.airbnb.service.AvailableService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AvailableServiceImpl implements AvailableService {
//
//    private final AvailableRepository availableRepository;
//    private final AccommodationRepository accommodationRepository;
//
//    public AvailableServiceImpl(AvailableRepository availableRepository, AccommodationRepository accommodationRepository) {
//        this.availableRepository = availableRepository;
//        this.accommodationRepository = accommodationRepository;
//    }
//
//    @Override
//    public List<Available> findAll() {
//        return this.availableRepository.findAll();
//    }
//
//    @Override
//    public Available create(AvailabaleDTO availabaleDTO) throws Exception {
//        Accommodation accommodation = accommodationRepository.findById(availabaleDTO.getAccommodationId())
//                .orElseThrow(Exception::new);
//
//        Available available = new Available();
//        available.setDate_from(availabaleDTO.getDate_from());
//        available.setDate_to(availabaleDTO.getDate_to());
//        available.setPrice(availabaleDTO.getPrice());
//        available.setAccommodation(accommodation);
//
//        return availableRepository.save(available);
//    }
//
//    @Override
//    public Available update(Long id, AvailabaleDTO availabaleDTO) throws Exception {
//        Available available = availableRepository.findById(id)
//                .orElseThrow(Exception::new);
//
//        Accommodation accommodation = accommodationRepository.findById(availabaleDTO.getAccommodationId())
//                .orElseThrow(Exception::new);
//
//        available.setDate_from(availabaleDTO.getDate_from());
//        available.setDate_to(availabaleDTO.getDate_to());
//        available.setPrice(availabaleDTO.getPrice());
//        available.setAccommodation(accommodation);
//
//        return availableRepository.save(available);
//    }
//
//    @Override
//    public void delete(Long id) {
//        if (availableRepository.existsById(id)) {
//            availableRepository.deleteById(id);
//        }
//    }
//}
