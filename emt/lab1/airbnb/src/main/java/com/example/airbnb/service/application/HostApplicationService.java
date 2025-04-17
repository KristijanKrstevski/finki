package com.example.airbnb.service.application;

import com.example.airbnb.dto.create.CreateHostDTO;
import com.example.airbnb.dto.display.DisplayHostDTO;
import com.example.airbnb.model.domains.Host;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDTO> findAll();
    Optional<DisplayHostDTO> findById(long id);
    Host create(CreateHostDTO hostDTO) throws Exception;

    Host update(Long id, CreateHostDTO hostDTO) throws Exception;

    void delete(Long id);
}
