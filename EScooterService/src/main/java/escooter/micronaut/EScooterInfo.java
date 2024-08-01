package escooter.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;


@Serdeable
public class EScooterInfo extends EScooter {
    public EScooterInfo(@Nullable final String id, @NonNull final EScooterState state, @Nullable final Location location) {
        super(state, location);
        this.setId(id);
    }


    @Override
    public String toString() {
        return "EScooterInfo{"+ "id=" + this.getId() +
                " state=" + this.getState() +
                " location=" + this.getLocation() +"}";
    }
}
