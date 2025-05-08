package com.example.airbnb.web.controller;

import com.example.airbnb.dto.create.CreateHostDTO;
import com.example.airbnb.model.views.HostByCountry;
import com.example.airbnb.projection.HostProjection;
import com.example.airbnb.service.application.HostApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Hosts", description = "Host management API for hosts")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all hosts")
    public ResponseEntity<?> listAllAuthors() {
        return ResponseEntity.status(HttpStatus.OK).body(hostApplicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "List specific hosts")
    public ResponseEntity<?> listAuthor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hostApplicationService.findById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new host", description = "Adds an hosts with details provided in the request body")
    public ResponseEntity<?> addAuthor(@RequestBody CreateHostDTO authorDto) throws Exception {
        return ResponseEntity.ok(hostApplicationService.create(authorDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an host", description = "Edits an existing host's details")
    public ResponseEntity<?> editAuthor(@RequestBody CreateHostDTO authorDto, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(hostApplicationService.update(id, authorDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an host", description = "Deletes an host by its ID")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        hostApplicationService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/by-country")
    public List<HostByCountry> getHostsByCountry() {
        return hostApplicationService.getHostsByCountry();
    }

    @GetMapping("/names")
    public List<HostProjection> getAllUserNames() {
        return hostApplicationService.takeNameAndSurnameByProjection();
    }
}
