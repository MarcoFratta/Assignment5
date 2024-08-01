package com.escooter.domain_layer.entities.implementations;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Document(collection = "rides")
public class Ride  {

	@Id
	private String id;
	private Date startedDate;
	private User user;

	private EScooter scooter;

	private Date endDate;


	public Ride() {
		this(null, null, null, null, null);
	}
	public Ride(final User user, final EScooter scooter,
				final Date startedDate) {
		this(null, user, scooter, startedDate, null);
	}

	public Ride(final User userId,
				final EScooter scooterId,
				final Date startTimestamp, final Date endTimestamp) {
		this(null, userId, scooterId, startTimestamp, endTimestamp);
	}

	public Ride(final String id, final User userId,
				final EScooter scooterId,
				final Date startTimestamp, final Date endTimestamp) {
		this.id = id;
		this.user = userId;
		this.scooter = scooterId;
		this.startedDate = startTimestamp;
		this.endDate = endTimestamp;
	}


	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public void setScooter(final EScooter scooter) {
		this.scooter = scooter;
	}

	public Date getStartedDate() {
		return this.startedDate;
	}
    public void setStartedDate(final Date startedDate) {
		this.startedDate = startedDate;
	}


	public EScooter getScooter() {
		return this.scooter;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public boolean isOngoing() {
		return this.endDate == null;
	}
	

	public Optional<Date> getEndDate() {
		return Optional.ofNullable(this.endDate);
	}


	public User getUser() {
		return this.user;
	}


	public EScooter getEScooter() {
		return this.scooter;
	}


	public void endRide() {
		this.endDate = new Date();
	}


	public boolean equals(final Object o) {
		if (this == o) return true;
		if (!(o instanceof Ride ride)) return false;
        return Objects.equals(this.id, ride.id);
	}


	public int hashCode() {
		return Objects.hash(this.id);
	}


	public String toString() {
		return "RideImpl{" +
				"startedDate=" + this.startedDate +
				", endDate=" + this.endDate +
				", user=" + this.user +
				", scooter=" + this.scooter +
				", id='" + this.id + '\'' +
				'}';
	}
}
