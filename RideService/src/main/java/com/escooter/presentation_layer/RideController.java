package com.escooter.presentation_layer;

import com.escooter.domain_layer.entities.implementations.EScooter;
import com.escooter.domain_layer.entities.implementations.Ride;
import com.escooter.domain_layer.entities.implementations.User;
import com.escooter.service_layer.RideService;
import com.escooter.service_layer.entities.exceptions.RideAlreadyEndedException;
import com.escooter.service_layer.entities.exceptions.RideNotFoundException;
import com.escooter.service_layer.entities.exceptions.RideNotPossibleException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class RideController {

    private final RideService service;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RideController(final RideService service) {
        this.service = service;
    }

    @GetMapping("api/rides")
    public ResponseEntity<List<Ride>> list() {
        this.logger.info("Get on going rides request");
        return ResponseEntity.ok(this.service.getOnGoingRides());
    }

    @PostMapping("api/rides")
    public ResponseEntity<Object> save(@RequestBody @Valid @NotNull final NewRideMessage message) {
        try {
            this.logger.info("New ride request - {}", message.toString());
            final Ride ride = this.service.startNewRide(message.userId(), message.eScooterId());
            return ResponseEntity.status(HttpStatus.CREATED).body(ride);
        } catch (final RideNotPossibleException e) {
            this.logger.error("Cannot start ride  - {}", message.toString());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("api/rides/{id}")
    public ResponseEntity<Optional<Ride>> find(@PathVariable final String id) {
        this.logger.info("Get ride request - {}", id);
        final Optional<Ride> ride = this.service.getRide(id);
        return ride.isPresent() ? ResponseEntity.ok(ride) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("api/rides/{id}/stop")
    public ResponseEntity<Void> stop(@PathVariable final String id) {
        try {
            this.service.stopRide(id);
            return ResponseEntity.ok().build();
        } catch (final RideNotFoundException e) {
            this.logger.error("Cannot stop ride, ride not found  - {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (final RideAlreadyEndedException e) {
            this.logger.error("Cannot stop ride, ride already stopped  - {}", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
