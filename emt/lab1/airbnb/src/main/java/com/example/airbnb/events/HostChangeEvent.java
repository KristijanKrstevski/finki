package com.example.airbnb.events;

        import com.example.airbnb.model.domains.Host;
        import org.springframework.context.ApplicationEvent;

        import java.time.LocalDateTime;

public class HostChangeEvent extends ApplicationEvent {
    private LocalDateTime when;

    public HostChangeEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }
    public HostChangeEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
    public LocalDateTime getWhen() {
        return when;
    }
}

