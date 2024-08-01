package com.escooter.domain_layer.entities.implementations;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Document(collection = "eScooter")
public class EScooter {

	@Id
	private String id;
	private Location location;
	private EScooterState state;

	public EScooter(final String id, final EScooterState state, final Location location) {
        this.id = id;
        this.state = state;
		this.location = location;
	}

	// Getters and setters

	public void setId(final String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public EScooterState getState() {
		return this.state;
	}
	public void setState(final EScooterState state) {
		this.state = state;
	}

	public Optional<Location> getLocation() {
		return Optional.ofNullable(this.location);
	}

	public void setLocation(final Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "EScooter{" +
				"id='" + this.id + '\'' +
				", location=" + this.location +
				", state=" + this.state +
				'}';
	}
}
