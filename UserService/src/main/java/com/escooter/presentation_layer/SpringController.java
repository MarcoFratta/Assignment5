package com.escooter.presentation_layer;

import com.escooter.service_layer.ServicePort;
import com.escooter.service_layer.exceptions.UserNotFoundException;
import com.escooter.service_layer.entities.implementations.NewUserRequest;
import com.escooter.service_layer.entities.implementations.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class SpringController {

    private final ServicePort service;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SpringController(final ServicePort service) {
        this.service = service;
    }

    @GetMapping("api/users/{id}")
    public ResponseEntity<UserInfo> getUser(@PathVariable("id") final String id) {
        this.logger.info("Get user request - {}", id);
        try {
            final var user = this.service.getUser(id);
            return ResponseEntity.ok(user);
        } catch (final UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("api/users")
    public ResponseEntity<String> createUser(@RequestBody final NewUserRequest userInfo) {
        try {
            this.logger.info("New user request - {}", userInfo.toString());
            final var user = this.service.registerNewUser(userInfo.name(),
                    userInfo.surname());
            final var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.id())
                    .toUri();
            return ResponseEntity.created(location)
                    .body("User created with ID: " + user.id());
        } catch (final Exception e) {
            this.logger.error("Error creating new user request");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user: " + e.getMessage());
        }
    }
}
