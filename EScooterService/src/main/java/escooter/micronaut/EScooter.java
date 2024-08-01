package escooter.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import java.util.Optional;

@MappedEntity
public class EScooter {


	@Nullable
	private Location location;
	@NonNull
	private int state;
	@Id
	@GeneratedValue
	private String id;



	public EScooter(@NonNull final EScooterState state, @Nullable final Location location) {
		this.state = state.ordinal();
		this.location = location;
	}


	// Getters and setters

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public EScooterState getState() {
		return EScooterState.values()[this.state];
	}
	public void setState(final EScooterState state) {
		this.state = state.ordinal();
	}

	public Optional<Location> getLocation() {
		return Optional.ofNullable(this.location);
	}

	public void setLocation(final Location location) {
		this.location = location;
	}
}
