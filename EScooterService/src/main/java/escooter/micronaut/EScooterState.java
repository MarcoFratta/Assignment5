package escooter.micronaut;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public enum EScooterState {
    AVAILABLE, IN_USE, MAINTENANCE
}
