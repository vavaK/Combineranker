import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadInformation {
    private static ArrayList<String> input;
    public ReadInformation(String path) throws IOException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        input = new ArrayList<String>();
        while(scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }
    }

    public ArrayList<String> getInput(){
        return input;
    }
}
