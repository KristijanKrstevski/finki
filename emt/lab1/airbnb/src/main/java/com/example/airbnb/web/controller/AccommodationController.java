package com.example.airbnb.web.controller;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.dto.AccommodationDTO;
import com.example.airbnb.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationController) {
        this.accommodationService = accommodationController;
    }
    @GetMapping
    public ResponseEntity<List<Accommodation>> findAll() {
        return ResponseEntity.ok(this.accommodationService.findAll());
    }

    @PostMapping("/add-accommodation")

    public ResponseEntity<Accommodation> addBooking(@RequestBody AccommodationDTO bookingDTO) throws Exception {
        return ResponseEntity.ok(this.accommodationService.create(bookingDTO));
    }

    @PutMapping("/edit-accommodation/{ID}")

    public ResponseEntity<Accommodation> editBooking(@PathVariable Long ID,
                                               @RequestBody AccommodationDTO bookingDTO) throws Exception {
        return ResponseEntity.ok(this.accommodationService.update(ID, bookingDTO));
    }
    @PutMapping("/reserve/{id}")
    public ResponseEntity<Accommodation> reserveAccommodation(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.accommodationService.reservation(id));
    }
}
