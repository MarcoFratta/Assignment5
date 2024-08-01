package com.escooter.domain_layer.entities.implementations;


import java.util.Objects;


public class Location {


    private final double latitude;
    private final double longitude;

    public Location(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final Location location)) return false;
        return Double.compare(this.latitude, location.latitude) == 0 && Double.compare(this.longitude, location.longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.latitude, this.longitude);
    }
}
