package escooter.micronaut;


import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.util.Objects;

@Serdeable
public class Location{

    @NonNull
    private final double latitude;
    @NonNull
    private final double longitude;

    public Location(@NonNull final double latitude, @NonNull final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public double getLatitude() {
        return this.latitude;
    }

    @NonNull
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
