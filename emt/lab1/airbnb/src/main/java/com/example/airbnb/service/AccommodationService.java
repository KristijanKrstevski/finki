package com.example.airbnb.service;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.dto.AccommodationDTO;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Accommodation create(AccommodationDTO accommodationDTO) throws Exception;
    Accommodation update(Long id, AccommodationDTO accommodationDTO) throws Exception;
    void delete(Long id);
    Accommodation reservation(Long id) throws Exception;
}
