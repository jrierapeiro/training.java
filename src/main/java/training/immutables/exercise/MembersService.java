package training.immutables.exercise;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MembersService{
    public List<Member> GetStaticMembers(){
        return Arrays.asList(
                BuildMember("Amazon", "UK",
                        Arrays.asList(
                                LocalDate.of(2018,1,1),
                                LocalDate.of(2018,1,2),
                                LocalDate.of(2018,1,3)
                        )),
                BuildMember("HubSpot", "IE",
                        Arrays.asList(
                                LocalDate.of(2018,1,1),
                                LocalDate.of(2018,1,2),
                                LocalDate.of(2018,1,3)
                        )),
                BuildMember("Ancestry", "US",
                        Arrays.asList(
                                LocalDate.of(2018,1,1),
                                LocalDate.of(2018,1,2),
                                LocalDate.of(2018,1,3)
                        )),
                BuildMember("Gilt", "IE",
                        Arrays.asList(
                                LocalDate.of(2018,1,2),
                                LocalDate.of(2018,1,3)
                        )),
                BuildMember("Workday", "US",
                        Arrays.asList(
                                LocalDate.of(2018,1,2),
                                LocalDate.of(2018,1,3)
                        )),
                BuildMember("Google", "US",
                        Arrays.asList(
                                LocalDate.of(2018,1,1),
                                LocalDate.of(2018,1,3)
                        ))
        );
    }

    public void ComputeMembers(List<Member> members){
        // Group members by country
        Map<String, List<Member>> membersByCountry = getMembersByCountry(members);
        assert membersByCountry.keySet().size() == 3; // Only 3 countries

        // Generate Events
        List<Event> events = new ArrayList();
        membersByCountry.forEach(
                (country, countryMembers) -> {
                    Event twoDayEvent = getTwoDayEvent(countryMembers);
                    if(twoDayEvent != null){
                        twoDayEvent.setCountry(country);
                        events.add(twoDayEvent);
                    }
                }
        );

        assert events.size() == 3;
    }

    private Map<String, List<Member>> getMembersByCountry(List<Member> members) {
        return members
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Member::country
                        )
                );
    }

    private Event getTwoDayEvent(List<Member> members){
        // Generate map of members with two consecutive days available
        Map<LocalDate, List<String>> datesWithAttendants = new HashMap<>();
        members.forEach(
                m -> {
                    // At least two dates
                    if(m.availableDates().size() >= 2){
                        m.availableDates().forEach(
                                d -> {
                                    if(m.availableDates().contains(d.plusDays(1))){
                                        datesWithAttendants
                                                .computeIfAbsent(
                                                        d,
                                                        l -> new ArrayList<>()
                                                );
                                        datesWithAttendants.get(d).add(m.name());
                                    }
                                }
                        );
                    }
                }
        );

        // Not possible
        if(datesWithAttendants.size() == 0){
            return null;
        }

        LocalDate bestDate = datesWithAttendants.entrySet()
                .stream()
                .max((e1, e2) -> e1.getValue().size() > e2.getValue().size() ? 1 : -1)
                .get()
                .getKey();

        Event event = new Event();
        event.setAttendees(datesWithAttendants.get(bestDate));
        event.setStartDate(bestDate);

        return event;
    }

    private Member BuildMember(String name, String country, List<LocalDate> dates){
        return ImmutableMember
                .builder()
                .name(name)
                .addAllAvailableDates(dates)
                .country(country)
                .build();
    }
}
