package com.example.airbnb.service.application;

import com.example.airbnb.dto.create.CreateAccommodationDTO;
import com.example.airbnb.dto.display.DisplayAccommodationDTO;
import com.example.airbnb.model.domains.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService{
    List<DisplayAccommodationDTO> findAll();
    Optional<DisplayAccommodationDTO> findById(long id);
    Accommodation create(CreateAccommodationDTO accommodationDTO) throws Exception;
    Accommodation update(Long id, CreateAccommodationDTO accommodationDTO) throws Exception;
    void delete(Long id);
    Accommodation reservation(Long id) throws Exception;
}
