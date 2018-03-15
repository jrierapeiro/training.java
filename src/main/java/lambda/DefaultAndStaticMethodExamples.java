package lambda;

import common.ICustomExamples;
import org.omg.CORBA.Object;

import java.util.Objects;

public class DefaultAndStaticMethodExamples implements ICustomExamples {

    public void RunExamples(){
        PredicatesCombinedExample();
    }

    private static void PredicatesCombinedExample(){
        Predicate<String> p1 = s -> s.length() < 20;
        Predicate<String> p2 = s -> s.length() > 10;
        Predicate<String> p3 = p1.and(p2);

        assert p3.test("ABCDEFGHIJ");
        System.out.println("PredicatesExample: OK");
    }

    private static void PredicatesStaticMethodExample(){

        // Predicate<String> p1 = Predicate.isEqual(new String("a"));
        System.out.println("PredicatesStaticMethodExample: OK");
    }

    private interface Predicate<T>{
        boolean test(T t);

        default Predicate<T> and(Predicate<? super T> other){
            Objects.requireNonNull(other);
            return (t) -> test(t) && other.test(t);
        }

        static <T> Predicate<T> isEqual(Object targetRef){
            return (null == targetRef) ? Objects::isNull : object -> targetRef.equals(object);
        }
    }
}
