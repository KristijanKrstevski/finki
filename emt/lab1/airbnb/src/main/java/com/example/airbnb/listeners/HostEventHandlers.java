package com.example.airbnb.listeners;

import com.example.airbnb.events.HostChangeEvent;
import com.example.airbnb.events.HostCreatedEvent;
import com.example.airbnb.events.HostDeletedEvent;
import com.example.airbnb.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {

    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event) {
        hostService.refreshMaterializedView();
    }

    @EventListener
    public void onHostDeleted(HostDeletedEvent event) {
        hostService.refreshMaterializedView();
    }

    @EventListener
    public void onHostChanged(HostChangeEvent event) {
        hostService.refreshMaterializedView();
    }
}
