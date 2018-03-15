package training.immutables.auto;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Scooter implements Vehicle {
    public abstract static class Builder implements VehicleBuilder {}
}
