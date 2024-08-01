package com.escooter.service_layer;

import com.escooter.domain_layer.entities.implementations.EScooterState;
import com.escooter.data_source_layer.RideRepository;
import com.escooter.domain_layer.entities.implementations.EScooter;
import com.escooter.domain_layer.entities.implementations.Ride;
import com.escooter.domain_layer.entities.implementations.User;
import com.escooter.service_layer.entities.exceptions.RideNotPossibleException;
import com.escooter.service_layer.entities.exceptions.RideAlreadyEndedException;
import com.escooter.service_layer.entities.exceptions.RideNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;



    @Autowired
    public RideServiceImpl(final RideRepository rideRepository,
                           final DiscoveryClient discoveryClient, final RestClient.Builder restClientBuilder) {
        this.rideRepository = rideRepository;
        this.restClient = restClientBuilder.build();
        this.discoveryClient = discoveryClient;
    }

    @Override
    public Optional<Ride> getRide(final String rideId) {
        return this.rideRepository.findById(rideId);
    }

    @Override
    public Ride startNewRide(final String userId, final String eScooterId) throws RideNotPossibleException {
            final var userInstance = this.discoveryClient.getInstances("user-service").get(0);
            final User existingUser = this.restClient.get()
                    .uri(userInstance.getUri() + "/api/users/" + userId)
                    .retrieve()
                    .body(User.class);

            final var scooterInstance = this.discoveryClient.getInstances("escooter-service").get(0);
            final EScooter existingScooter = this.restClient.get()
                    .uri(scooterInstance.getUri() + "/api/escooters/" + eScooterId)
                    .retrieve()
                    .body(EScooter.class);
            System.out.println("Existing user: " + existingUser);
            System.out.println("Existing scooter: " + existingScooter);
            if(existingUser == null ){
                throw new RideNotPossibleException("User not found");
            }
            if(existingScooter == null) {
                throw new RideNotPossibleException("Scooter not found");
            }
            if(existingScooter.getState() != EScooterState.AVAILABLE) {

                throw new RideNotPossibleException("The scooter is not available");
            }
            final var ride = new Ride(existingUser, existingScooter, new Date());
            final var savedRide = this.rideRepository.save(ride);
            existingScooter.setState(EScooterState.IN_USE);
            final var res = this.restClient.put()
                    .uri(scooterInstance.getUri() + "/api/escooters/"+ eScooterId)
                    .body(existingScooter)
                    .retrieve()
                    .toBodilessEntity();
            return savedRide;

    }

    @Override
    public void stopRide(final String id) throws RideNotFoundException, RideAlreadyEndedException {
        final var ride = this.rideRepository.findById(id);
        if(ride.isEmpty()) {
            throw new RideNotFoundException();
        }
        final var rideToStop = ride.get();
        if(!rideToStop.isOngoing()) {
            throw new RideAlreadyEndedException();
        }
        rideToStop.endRide();
        this.rideRepository.save(rideToStop);
        final EScooter scooter = rideToStop.getEScooter();
        scooter.setState(EScooterState.AVAILABLE);
        final var scooterInstance = this.discoveryClient.getInstances("escooter-service").get(0);
        this.restClient.put()
                .uri(scooterInstance.getUri() + "/api/escooters/" + scooter.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(scooter)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public List<Ride> getOnGoingRides() {
        return this.rideRepository.findAll()
                .stream()
                .filter(Ride::isOngoing).toList();
    }
}