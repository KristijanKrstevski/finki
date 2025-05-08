package com.example.airbnb.service.domain.impl;

import com.example.airbnb.events.HostChangeEvent;
import com.example.airbnb.events.HostCreatedEvent;
import com.example.airbnb.events.HostDeletedEvent;
import com.example.airbnb.model.domains.Host;
import com.example.airbnb.projection.HostProjection;
import com.example.airbnb.repository.HostRepository;
import com.example.airbnb.repository.view.HostByCountryViewRepository;
import com.example.airbnb.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final HostByCountryViewRepository hostByCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    public HostServiceImpl(HostRepository hostRepository, HostByCountryViewRepository hostByCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.hostByCountryViewRepository = hostByCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Host save(Host host) {
        Host savedHost = hostRepository.save(host);
//        this.refreshMaterializedView();
        this.applicationEventPublisher.publishEvent(new HostCreatedEvent(savedHost));
        return savedHost;
    }

    @Override
    public Host update(Long id, Host host) throws Exception {
        Host existingHost = hostRepository.findById(id).orElseThrow(Exception::new);

        existingHost.setName(host.getName());
        existingHost.setSurname(host.getSurname());
        existingHost.setCountry(host.getCountry());

        Host updatedHost = hostRepository.save(existingHost);
//        this.refreshMaterializedView();
        this.applicationEventPublisher.publishEvent(new HostChangeEvent(updatedHost));
        return updatedHost;
    }


    @Override
    public void delete(Long id) {
        Host host = hostRepository.findById(id).orElseThrow();
        hostRepository.deleteById(id);
        this.applicationEventPublisher.publishEvent(new HostDeletedEvent(host));
    }

    @Override
    public void refreshMaterializedView() {
        this.hostByCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostProjection> takeNameAndSurnameByProjection() {
        return hostRepository.takeNameAndSurnameByProjection();
    }
}
