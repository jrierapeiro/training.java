import lambda.DefaultAndStaticMethodExamples;
import lambda.LambdaExamples;
import streams.SteamAPIs;

public class ExamplesRunner {
    public static void main(String[] args){
        new LambdaExamples().RunExamples();
        new DefaultAndStaticMethodExamples().RunExamples();
        new SteamAPIs().RunExamples();
        new date.and.time.Examples().RunExamples();
    }
}
