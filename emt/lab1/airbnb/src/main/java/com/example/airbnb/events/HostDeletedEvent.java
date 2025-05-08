package com.example.airbnb.events;

import com.example.airbnb.model.domains.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class HostDeletedEvent extends ApplicationEvent {
    private LocalDateTime when;

    public HostDeletedEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }
    public HostDeletedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }

    public LocalDateTime getWhen() {
        return when;
    }
}
