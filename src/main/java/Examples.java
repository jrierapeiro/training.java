import java.io.File;
import java.io.FileFilter;

public class Examples {

    public void AnonymousClassExample(){
        // Anonymous class definition
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().endsWith(".java");
            }
        };

        // Lambda definition
        FileFilter lambdaFileFilter = (File file) -> file.getName().endsWith(".java");
    }
}
