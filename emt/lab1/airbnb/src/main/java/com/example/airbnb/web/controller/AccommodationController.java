package com.example.airbnb.web.controller;

import com.example.airbnb.dto.create.CreateAccommodationDTO;
import com.example.airbnb.model.domains.Accommodation;
//import com.example.airbnb.model.domains.Available;
//import com.example.airbnb.dto.AccommodationDTO;
//import com.example.airbnb.dto.AvailabaleDTO;
//import com.example.airbnb.service.AvailableService;
import com.example.airbnb.service.application.AccommodationApplicationService;
import com.example.airbnb.service.domain.AccommodationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;
//    private final AvailableService availableService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService
                                   //, AvailableService availableService
                                   ) {
        this.accommodationApplicationService = accommodationApplicationService;
//        this.availableService = availableService;
    }


    @GetMapping
    @Operation(summary = "List all accommodation")
    public ResponseEntity<?> listAllAccommodation() {
        return ResponseEntity.status(HttpStatus.OK).body(accommodationApplicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "List specific accommodation")
    public ResponseEntity<?> findAccommodationById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accommodationApplicationService.findById(id));
    }



    @PostMapping("/add")
    @Operation(summary = "Add a new accommodation", description = "Adds a accommodation with details provided in the request body")
    public ResponseEntity<?> addAccommodation(@RequestBody CreateAccommodationDTO bookDto) throws Exception {
        return ResponseEntity.ok(accommodationApplicationService.create(bookDto));
    }


    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit a Accommodation", description = "Edits an existing accommodation's details")
    public ResponseEntity<?> editBook(@RequestBody CreateAccommodationDTO bookDto, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(accommodationApplicationService.update(id, bookDto));
    }

    @PutMapping("/reserve/{id}")
    public ResponseEntity<Accommodation> reserveAccommodation(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.accommodationApplicationService.reservation(id));
    }







//    @GetMapping("/{accommodationId}/availables")
//    public ResponseEntity<List<Available>> findAllAvailables(@PathVariable Long accommodationId) {
//
//        List<Available> availableList = availableService.findAll();
//        return ResponseEntity.ok(availableList);
//    }
//
//    @PostMapping("/{accommodationId}/add-available")
//    public ResponseEntity<Available> addAvailable(@PathVariable Long accommodationId, @RequestBody AvailabaleDTO availabaleDTO) throws Exception {
//        availabaleDTO.setAccommodationId(accommodationId);
//        Available available = availableService.create(availabaleDTO);
//        return ResponseEntity.ok(available);
//    }
//
//    @PutMapping("/edit-available/{id}")
//    public ResponseEntity<Available> editAvailable(@PathVariable Long id, @RequestBody AvailabaleDTO availabaleDTO) throws Exception {
//        Available available = availableService.update(id, availabaleDTO);
//        return ResponseEntity.ok(available);
//    }
//
//    @DeleteMapping("/delete-available/{id}")
//    public ResponseEntity<Void> deleteAvailable(@PathVariable Long id) {
//        availableService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
