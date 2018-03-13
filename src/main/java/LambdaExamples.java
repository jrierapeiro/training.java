import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Lambda:
// Type: functional interface. Interface with one abstract method
// Lambda vs objects: Better performance. It's not an object but it's an object without identity
public class LambdaExamples {
    public static void LambdaFileFilterExample(){
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

        System.out.println("LambdaFileFilterExample: OK");
    }

    public static void LambdaStringComparatorExample(){
        Comparator<String> stringComparator = (String s1, String s2) -> Integer.compare(s1.length(), s2.length());
        List<String> list = Arrays.asList("***","**","****","*");
        Collections.sort(list, stringComparator);
        Integer lastLength = 0;
        for(String s : list){
            assert s.length() >= lastLength;
            lastLength = s.length();
        }

        System.out.println("LambdaStringComparatorExample: OK");
    }
}
