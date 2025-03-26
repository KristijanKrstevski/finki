package com.example.airbnb.model.domains;

import jakarta.persistence.*;
import lombok.Data;


@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    private Country country;

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Host() {

    }

    public Long getId() {
        return id;
    }
}
