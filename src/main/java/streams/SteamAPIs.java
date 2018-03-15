package streams;

import com.sun.javafx.collections.MappingChange;
import common.ICustomExamples;
import lambda.FunctionalInterfaceExamples;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Stream: Map, filter, or reduce operations; not hold any data; not change data
// Efficiently:
// - In parallel, to leverage the computing power of multicore CPUs
// - Pipeline, to avoid unncessary computations
// Collection is not a Stream
// The output stream of a filter action is nothing! Stream does not hold data
// All methods of Stream that return another Stream are Lazy (intermediary operation)
public class SteamAPIs implements ICustomExamples {

    @Override
    public void RunExamples() {
        this.BuildingAndConsumingSteams();
        this.FilterStreams();
        this.IntermediaryAndFinalOperations();
        this.MapAndFlatMapOperations();
        this.ReductionOperation();
        this.OptionalExamples();
        this.CollectorsExamples();
    }

    private void BuildingAndConsumingSteams(){
        List<String> result = new ArrayList<String>();
        List<String> source = Arrays.asList("a", "b");

        Consumer<String> addConsumer = result::add;
        Consumer<String> printConsumer = System.out::println;

        source.stream().forEach(addConsumer.andThen(printConsumer));

        assert source.size() == result.size();
        System.out.println("BuildingAndConsumingSteams: OK");
    }

    private void FilterStreams(){
        List<Integer> source = Arrays.asList(5, 10, 25);

        Predicate<Integer> minPredicate = i -> i > 7;
        Predicate<Integer> maxPredicate = i -> i < 14;

        Predicate<Integer> unionPredicate = minPredicate.and(maxPredicate);
        Predicate<Integer> orPredicate = minPredicate.or(maxPredicate);

        assert source.stream().filter(unionPredicate).count() == 1;
        assert source.stream().filter(orPredicate).count() == 3;
        System.out.println("FilterStreams: OK");
    }

    private void IntermediaryAndFinalOperations(){
        Stream<Integer> source = Stream.of(5, 10, 25);
        Predicate<Integer> minPredicate = i -> i > 7;
        Predicate<Integer> maxPredicate = i -> i < 14;
        List<Integer> output = new ArrayList<Integer>();

        source
                .peek(System.out::println) // Not executed
                .filter(minPredicate.and(maxPredicate))
                .peek(output::add); // Intermediary operation: Not executed

        // THe list should ne empty because the Stream is empty
        assert output.size() == 0;

         // We cannot use the same stream => We already have operations pending
        Stream<Integer> newSource = Stream.of(5, 10, 25);

        newSource
                .peek(System.out::println) // Intermediary operation: Not executed
                .filter(minPredicate.and(maxPredicate))
                .forEach(output::add); // Final operation

        assert output.size() == 1;

        System.out.println("IntermediaryAndFinalOperations: OK");
    }

    private void MapAndFlatMapOperations(){
        List<Integer> source1 = Arrays.asList(1,2,3,4,5,6,7);
        List<Integer> source2 = Arrays.asList(2,4,6);
        List<Integer> source3 = Arrays.asList(1,3,5,7);

        List<List<Integer>> composition1 = Arrays.asList(source1, source2, source3);
        assert  composition1.get(0) instanceof List;

        Function<List<?>, Integer> size = List::size;
        List<Integer> output1 = new ArrayList();

        composition1.stream()
                .map(size)
                .forEach(output1::add);

        assert output1.get(0) == 7;
        assert output1.get(1) == 3;
        assert output1.get(2) == 4;


        List<Integer> output2 = new ArrayList();
        // Rather than add the streams and have an array with 3 list,
        // it adds the content of the strems in the output
        Function<List<Integer>, Stream<Integer>> flatmapper = l -> l.stream();
        composition1.stream()
                .flatMap(flatmapper)
                .forEach(output1::add);

        assert output2.size() == 14;

        System.out.println("MapAndFlatMapOperations: OK");
    }

    private void ReductionOperation(){
        List<Integer> source1 = Arrays.asList(1,2,3);
        Stream<Integer> stream = source1.stream();
        Integer sum = stream.reduce(0, (a, i) -> a+ i);
        assert sum == 6;

        assert source1.stream().max(Comparator.naturalOrder()).get() == 3;
        assert source1.stream().min(Comparator.naturalOrder()).get() == 1;
        assert source1.stream().count() == 3;
        assert source1.stream().allMatch(i -> i > 0);
        assert source1.stream().noneMatch(i -> i > 10);
        assert source1.stream().anyMatch(i -> i > 2);
        assert source1.stream().findFirst().get() == 1;
        assert source1.stream().findAny().get() != null;

        System.out.println("ReductionOperation: OK");
    }

    private void OptionalExamples(){
        List<Integer> source1 = Arrays.asList(1,2,3);
        Stream<Integer> stream = source1.stream();
        // When source1 is empty, max is null not 0 so the result needs to be Optional
        Optional<Integer> max = stream.max(Comparator.naturalOrder());

        assert max.isPresent(); // has value
        assert max.get() == 3;


        System.out.println("OptionalExamples: OK");
    }

    // Mutable reductions
    private void CollectorsExamples(){
        List<String> source1 = Arrays.asList("The", "collect", "method");

        assert source1.stream().collect(Collectors.joining(" ")) == "The collect method";

        List<String> source2 = Arrays.asList("The", "abc", "method");

        Map<Integer, Long> result = source2.stream()
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.counting()
                        )
                );
        assert result.get(3) == 2; // 2 strings with length 3
        assert result.get(5) == 1; // 1 string with length 5
        System.out.println("CollectorsExamples: OK");
    }
}
