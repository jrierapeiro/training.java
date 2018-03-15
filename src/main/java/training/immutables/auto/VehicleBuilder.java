package training.immutables.auto;

public interface VehicleBuilder {
    // Generated builders will implement this method
    // It is compatible with signature of generated builder methods where
    // return type is narrowed to Scooter or Automobile
    Vehicle build();
}
