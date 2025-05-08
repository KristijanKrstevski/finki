package com.example.airbnb.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("SELECT * FROM accommodations_by_host")
@Immutable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationByHost {
    @Id
    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "num_accommodations")
    private Integer numAccommodations;
}
