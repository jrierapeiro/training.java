package training.immutables.auto;

public class Builders {
    void buildIt(VehicleBuilder builder) {
        Vehicle vehicle = builder.build();
    }

    void use() {
        buildIt(ImmutableScooter.builder());
        buildIt(ImmutableAutomobile.builder());
    }
}
