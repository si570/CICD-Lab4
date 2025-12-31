package atu.ie.lab4.service;

import atu.ie.lab4.exception.DuplicatePassengerException;
import atu.ie.lab4.exception.PassengerNotFoundException;
import atu.ie.lab4.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {

    private final List<Passenger> passengers = new ArrayList<>();

    public List<Passenger> getAllPassengers() {
        return passengers;
    }

    public Passenger getPassengerById(Long id) {
        return passengers.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new PassengerNotFoundException("Passenger with id " + id + " not found"));
    }

    public Passenger addPassenger(Passenger passenger) {
        boolean exists = passengers.stream()
                .anyMatch(p -> p.getId().equals(passenger.getId()));

        if (exists) {
            throw new DuplicatePassengerException(
                    "Passenger with id " + passenger.getId() + " already exists");
        }

        passengers.add(passenger);
        return passenger;
    }

    public Passenger updatePassenger(Long id, Passenger updated) {
        Passenger existing = getPassengerById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        return existing;
    }

    public void deletePassenger(Long id) {
        Passenger passenger = getPassengerById(id);
        passengers.remove(passenger);
    }
}

