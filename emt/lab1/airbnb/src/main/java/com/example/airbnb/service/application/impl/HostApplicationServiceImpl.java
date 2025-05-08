package com.example.airbnb.service.application.impl;

import com.example.airbnb.dto.create.CreateHostDTO;
import com.example.airbnb.dto.display.DisplayHostDTO;
import com.example.airbnb.model.domains.Country;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.model.views.HostByCountry;
import com.example.airbnb.projection.HostProjection;
import com.example.airbnb.repository.view.HostByCountryViewRepository;
import com.example.airbnb.service.application.HostApplicationService;
import com.example.airbnb.service.domain.CountryService;
import com.example.airbnb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {


    private final HostService hostService;
    private final CountryService countryService;

    private final HostByCountryViewRepository hostByCountryViewRepository;
    public HostApplicationServiceImpl(HostService hostService, CountryService countryService, HostByCountryViewRepository hostByCountryViewRepository) {
        this.hostService = hostService;
        this.countryService = countryService;
        this.hostByCountryViewRepository = hostByCountryViewRepository;
    }
    @Override
    public List<DisplayHostDTO> findAll() {
        return hostService.findAll()
                .stream()
                .map(DisplayHostDTO::from)
                .toList();
    }

    @Override
    public Optional<DisplayHostDTO> findById(long id) {
        return hostService.findById(id).map(DisplayHostDTO::from);
    }

    @Override
    public Host create(CreateHostDTO hostDTO) throws Exception {
        Country country = countryService.findById(hostDTO.countryId())
                .orElseThrow(Exception::new);

        Host host = hostDTO.toHost(country);
        return hostService.save(host);
    }

    @Override
    public Host update(Long id, CreateHostDTO hostDTO) throws Exception {
        Host existing = hostService.findById(id)
                .orElseThrow(Exception::new);

        Country country = countryService.findById(hostDTO.countryId())
                .orElseThrow(Exception::new);

        existing.setName(hostDTO.name());
        existing.setSurname(hostDTO.surname());
        existing.setCountry(country);

        return hostService.update(id, existing);
    }

    @Override
    public void delete(Long id) {
        hostService.delete(id);
    }

    @Override
    public List<HostByCountry> getHostsByCountry() {
        return hostByCountryViewRepository.findAll();
    }

    @Override
    public List<HostProjection> takeNameAndSurnameByProjection() {
        return hostService.takeNameAndSurnameByProjection();
    }
}