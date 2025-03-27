package com.example.airbnb.web.controller;

import com.example.airbnb.model.domains.Accommodation;
import com.example.airbnb.model.domains.Available;
import com.example.airbnb.model.dto.AccommodationDTO;
import com.example.airbnb.model.dto.AvailabaleDTO;
import com.example.airbnb.service.AccommodationService;
import com.example.airbnb.service.AvailableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final AvailableService availableService;

    public AccommodationController(AccommodationService accommodationService, AvailableService availableService) {
        this.accommodationService = accommodationService;
        this.availableService = availableService;
    }


    @GetMapping
    public ResponseEntity<List<Accommodation>> findAllAccommodations() {
        return ResponseEntity.ok(this.accommodationService.findAll());
    }

    @PostMapping("/add-accommodation")
    public ResponseEntity<Accommodation> addAccommodation(@RequestBody AccommodationDTO accommodationDTO) throws Exception {
        return ResponseEntity.ok(this.accommodationService.create(accommodationDTO));
    }

    @PutMapping("/edit-accommodation/{ID}")
    public ResponseEntity<Accommodation> editAccommodation(@PathVariable Long ID, @RequestBody AccommodationDTO accommodationDTO) throws Exception {
        return ResponseEntity.ok(this.accommodationService.update(ID, accommodationDTO));
    }

    @PutMapping("/reserve/{id}")
    public ResponseEntity<Accommodation> reserveAccommodation(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.accommodationService.reservation(id));
    }



    @GetMapping("/{accommodationId}/availables")
    public ResponseEntity<List<Available>> findAllAvailables(@PathVariable Long accommodationId) {

        List<Available> availableList = availableService.findAll();
        return ResponseEntity.ok(availableList);
    }

    @PostMapping("/{accommodationId}/add-available")
    public ResponseEntity<Available> addAvailable(@PathVariable Long accommodationId, @RequestBody AvailabaleDTO availabaleDTO) throws Exception {
        availabaleDTO.setAccommodationId(accommodationId);
        Available available = availableService.create(availabaleDTO);
        return ResponseEntity.ok(available);
    }

    @PutMapping("/edit-available/{id}")
    public ResponseEntity<Available> editAvailable(@PathVariable Long id, @RequestBody AvailabaleDTO availabaleDTO) throws Exception {
        Available available = availableService.update(id, availabaleDTO);
        return ResponseEntity.ok(available);
    }

    @DeleteMapping("/delete-available/{id}")
    public ResponseEntity<Void> deleteAvailable(@PathVariable Long id) {
        availableService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
