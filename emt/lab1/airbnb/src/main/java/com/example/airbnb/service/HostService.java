package com.example.airbnb.service;

import com.example.airbnb.model.domains.Host;

import java.util.List;

public interface HostService {
    List<Host> findAll();
}
