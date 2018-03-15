package date.and.time;

import common.ICustomExamples;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

// New API: java.time (replaces date and calendar classes)
public class Examples implements ICustomExamples {
    @Override
    public void RunExamples() {
        this.InstantClassExamples();
        this.LocalDateExamples();
        this.DateAjusterExamples();
        this.LocalTimeExamples();
        this.ZoneTimeExamples();
        this.FormatDateExamples();
    }

    // Instant it is a point on the time line
    // Immutable!
    private void InstantClassExamples (){
        Instant now = Instant.now();
        Instant end =  Instant.now();
        Duration elapsed = Duration.between(now, end);
        assert elapsed.toHours() == 0;
        System.out.println("InstantClassExamples: OK");
    }

    private void LocalDateExamples(){
        LocalDate now = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(1983, Month.NOVEMBER,10);
        Period elapsed = Period.between(dateOfBirth, now);
        assert elapsed.getYears() > 0;
        System.out.println("LocalDateExamples: OK");
    }

    private void DateAjusterExamples(){
        LocalDate now = LocalDate.now();
        LocalDate nextSunday = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        System.out.println("DateAjusterExamples: OK");
    }

    private void LocalTimeExamples(){
        LocalTime bedTime = LocalTime.of(23,0);
        LocalTime wakeUpTime = bedTime.plusHours(8);

        assert wakeUpTime.getHour() == 7;
        System.out.println("LocalTimeExamples: OK");
    }

    private void ZoneTimeExamples(){
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        ZoneId ukTimeZone = ZoneId.of("Europe/London");

        ZonedDateTime birthDate = ZonedDateTime.of(
                1983, Month.NOVEMBER.getValue(), 10,
                21, 0, 0,0,
                ZoneId.of("Europe/Madrid")
        );

        assert birthDate.toString().equals("1983-11-10T21:00+01:00[Europe/Madrid]");
        System.out.println("ZoneTimeExamples: OK");
    }

    private void FormatDateExamples(){
        ZonedDateTime birthDate = ZonedDateTime.of(
                1983, Month.NOVEMBER.getValue(), 10,
                21, 0, 0,0,
                ZoneId.of("Europe/Madrid")
        );

        DateTimeFormatter.ISO_DATE_TIME.format(birthDate);
        System.out.println("FormatDateExamples: OK");
    }
}
