package training.immutables;

import common.ICustomExamples;

public class Examples implements ICustomExamples {

    @Override
    public void RunExamples() {
        this.ImmutablesBuilder();
    }

    private void ImmutablesBuilder(){
        FoobarValue value = training.immutables.ImmutableFoobarValue.builder()
                .foo(2)
                .bar("Bar")
                .addBuz(1, 3, 4)
                .addCrux((long) 1.0)
                .build(); // FoobarValue{foo=2, bar=Bar, buz=[1, 3, 4], crux={}}

        assert value.crux().contains((long)1.0); // true
        assert value.foo() == 2; // 2
        assert value.buz().size() == 3; // ImmutableList.of(1, 3, 4)

        System.out.println("ImmutablesBuilder: OK");
    }
}
