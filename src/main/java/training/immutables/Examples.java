package training.immutables;

import common.ICustomExamples;
import training.immutables.examples.*;

public class Examples implements ICustomExamples {

    @Override
    public void RunExamples() {
        this.ImmutablesBuilder();
        this.MultipleDefinitions();
    }

    private void ImmutablesBuilder(){
        FoobarValue value = ImmutableFoobarValue
                .builder()
                .foo(2)
                .bar("Bar")
                .addBuz(1, 3, 4)
                .addCrux((long) 1.0)
                .build(); // FoobarValue{foo=2, bar=Bar, buz=[1, 3, 4], crux={}}

        assert value.crux().contains((long)1.0); // true
        assert value.foo() == 2; // 2
        assert value.buz().size() == 3; // ImmutableList.of(1, 3, 4)

        System.out.println("ImmutablesBuilder: OK");

        FoobarValue newValue = ImmutableFoobarValue
                .builder()
                .from(value)
                .bar("newBar")
                .build();

        assert newValue.bar() == "newBar";
        assert newValue.foo() == value.foo(); // Builder.From
    }

    private void MultipleDefinitions() {
        assert ImmutableValueInterface.builder().build() instanceof ValueInterface;
        assert ImmutableValueClass.builder().build() instanceof ValueClass;
        assert ImmutableValueAnnotation.builder().build() instanceof ValueAnnotation;
    }
}