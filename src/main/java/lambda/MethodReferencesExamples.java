package lambda;

import java.util.function.Consumer;

public class MethodReferencesExamples {
    public void Example(){
        Consumer<String> c = s -> System.out.println(s);
        Consumer<String> cc = System.out::println;
    }
}
