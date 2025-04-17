package com.example.airbnb.service.domain.impl;

import com.example.airbnb.model.domains.Host;
import com.example.airbnb.repository.HostRepository;
import com.example.airbnb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
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
        return hostRepository.save(host);
    }

    @Override
    public Host update(Long id, Host host) throws Exception {
        Host existingHost = hostRepository.findById(id).orElseThrow(Exception::new);

        existingHost.setName(host.getName());
        existingHost.setSurname(host.getSurname());
        existingHost.setCountry(host.getCountry());

        return hostRepository.save(existingHost);
    }

    @Override
    public void delete(Long id) {
        hostRepository.deleteById(id);
    }
}
