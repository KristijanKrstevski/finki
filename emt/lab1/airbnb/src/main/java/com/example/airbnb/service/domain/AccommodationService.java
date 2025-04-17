package com.example.airbnb.service.domain;

import com.example.airbnb.dto.create.CreateAccommodationDTO;
import com.example.airbnb.dto.display.DisplayAccommodationDTO;
import com.example.airbnb.model.domains.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Accommodation create(Accommodation accommodation) throws Exception;
    Accommodation update(Long id, Accommodation accommodation) throws Exception;
    void delete(Long id);
    Accommodation reservation(Long id) throws Exception;
    Optional<Accommodation> findById(Long id);

}
