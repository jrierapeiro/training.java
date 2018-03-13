import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class Examples {

    public static void main(String[] args){
        AnonymousClassExample();
    }

    public static void AnonymousClassExample(){
        // Anonymous class definition
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().endsWith(".java");
            }
        };

        // Lambda definition
        FileFilter lambdaFileFilter = (File file) -> file.getName().endsWith(".java");

        File dir = new File("./src/main/java");
        File[] files = dir.listFiles(fileFilter);
        File[] filesFromLambda = dir.listFiles(lambdaFileFilter);

        assert files.length == filesFromLambda.length;
        for(File f : files){
           assert Arrays.asList(filesFromLambda).contains(f);
        }

        System.out.println("AnonymousClassExample: OK");
    }
}
