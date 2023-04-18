import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Helper {
    static Scanner read(String filename) {
        try { return new Scanner(new File(filename)); } 
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            System.exit(1);
        }
        return null;
    }
}
