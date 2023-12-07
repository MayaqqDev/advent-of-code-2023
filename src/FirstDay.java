import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FirstDay {
    public static void run(String path) {
        int fullValue = 0;
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String numbersOnly = data.replaceAll("[^0-9]", "");
                String firstNumber = numbersOnly.substring(0, 1);
                String lastNumber = numbersOnly.substring(numbersOnly.length() - 1);
                String fullNumber = firstNumber + lastNumber;
                fullValue += Integer.parseInt(fullNumber);
            }
            reader.close();
            System.out.println(fullValue);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}