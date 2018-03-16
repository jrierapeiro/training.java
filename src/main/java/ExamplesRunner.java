import lambda.DefaultAndStaticMethodExamples;
import lambda.LambdaExamples;
import streams.SteamAPIs;
import training.immutables.exercise.ExerciseRunner;

public class ExamplesRunner {
    public static void main(String[] args){
        new LambdaExamples().RunExamples();
        new DefaultAndStaticMethodExamples().RunExamples();
        new SteamAPIs().RunExamples();
        new date.and.time.Examples().RunExamples();
        new io.Examples().RunExamples();
        new map.Examples().RunExamples();
        new training.immutables.Examples().RunExamples();
        new ExerciseRunner().RunExamples();
    }
}
