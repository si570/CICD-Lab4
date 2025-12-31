package atu.ie.lab4.controller;

import atu.ie.lab4.model.Passenger;
import atu.ie.lab4.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAll() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getById(@PathVariable Long id) {
        return ResponseEntity.ok(passengerService.getPassengerById(id));
    }

    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger passenger) {
        Passenger saved = passengerService.addPassenger(passenger);
        return ResponseEntity
                .created(URI.create("/api/passengers/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> update(
            @PathVariable Long id,
            @Valid @RequestBody Passenger passenger) {

        return ResponseEntity.ok(passengerService.updatePassenger(id, passenger));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }
}

