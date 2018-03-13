public class FunctionalInterfaceExamples {
    public interface Supplier<T>{
        T get();
    }

    public interface Consumer<T>{
        void accept(T t);
    }

    public interface BiConsumer<T, U> {
        void accept(T t, U u);
    }

    public interface Predicate<T>{
        boolean test(T t);
    }

    public interface Function<T, R>{
        R apply(T t);
    }
}
