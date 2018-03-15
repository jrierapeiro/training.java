package map;

import common.ICustomExamples;
import common.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Examples implements ICustomExamples {
    @Override
    public void RunExamples() {
        this.MapExamples();
        this.BiMapExamples();
    }

    private void MapExamples() {
        List<Person> persons = Arrays.asList(
                new Person("John", "M", 25),
                new Person("Tom", "N", 25),
                new Person("Mary", "F", 25),
                new Person("Alsda", "N", 20));

        List<Person> list1 = persons.subList(0, 2);
        List<Person> list2 = persons.subList(2, persons.size());

        Map<Integer, List<Person>> map1 = getMapByAge(list1);
        map1.forEach((age, list) -> System.out.println(age + " -> " + list));
        assert map1.get(25).size() == 2; //two with 25 age
        Map<Integer, List<Person>> map2 = getMapByAge(list2);
        map2.forEach((age, list) -> System.out.println(age + " -> " + list));
        assert map2.get(25).size() == 1; //one with 25 age

        // Merge by age
        map2.entrySet().stream()
                .forEach(
                        entry -> map1.merge(
                                entry.getKey(),
                                entry.getValue(),
                                (l1, l2) -> {
                                    l1.addAll(l2);
                                    return l1;
                                }
                        )
                );

        map1.forEach((age, list) -> System.out.println(age + " -> " + list));
        assert map1.get(25).size() == 3; //three with 25 age
    }

    private void BiMapExamples() {
        List<Person> persons = Arrays.asList(
                new Person("John", "M", 25),
                new Person("Tom", "N", 25),
                new Person("Mary", "F", 25),
                new Person("Alsda", "N", 20));

        Map<Integer, Map<String, List<Person>>> bimap = new HashMap<>();

        persons.forEach(
                p -> bimap.computeIfAbsent(
                        p.getAge(),
                        HashMap::new
                ).merge(
                        p.getGender(),
                        new ArrayList<Person>(Arrays.asList(p)),
                        (l1,l2) -> {
                            l1.addAll(l2);
                            return l1;
                        }

                )
        );

        bimap.forEach(
                (age, map) -> System.out.println(age + " -> "+ map)
        );
    }


    private static Map<Integer, List<Person>> getMapByAge(List<Person> list) {
        return list
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge
                        )
                );
    }
}
