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
}
