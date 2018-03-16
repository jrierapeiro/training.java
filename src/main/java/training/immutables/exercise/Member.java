package training.immutables.exercise;

import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.List;

@Value.Immutable
public abstract class Member {
    public abstract String country();
    public abstract String name();
    public abstract List<LocalDate> availableDates();
}

