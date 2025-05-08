package com.example.airbnb.model.domains;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccommodationRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    public AccommodationRent() {}

    public AccommodationRent(User user, Accommodation accommodation) {
        this.accommodation = accommodation;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }
}
