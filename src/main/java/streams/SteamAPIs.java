package streams;

import common.ICustomExamples;
import lambda.FunctionalInterfaceExamples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

// Stream: Map, filter, or reduce operations; not hold any data; not change data
// Efficiently:
// - In parallel, to levte erage the computing power of multicore CPUs
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
}
