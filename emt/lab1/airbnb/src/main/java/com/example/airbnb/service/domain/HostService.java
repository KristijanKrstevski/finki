package com.example.airbnb.service.domain;

import com.example.airbnb.model.domains.Host;
import com.example.airbnb.projection.HostProjection;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Host save(Host host);
    Host update(Long id, Host host) throws Exception;
    void delete(Long id);

    void refreshMaterializedView();

    List<HostProjection> takeNameAndSurnameByProjection();

}
