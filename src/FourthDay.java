import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FourthDay {
    public static void run(String path) {
        int fullValue = 0;
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {

                int linePoints = 0;

                String data = reader.nextLine().split(": ")[1].replaceAll("  ", " ");

                String[] cardNumbers = data.split("\\|")[0].split(" ");

                String[] myNumbers = data.split("\\|")[1].split(" ");

                HashMap<Integer, Boolean> isIn = new HashMap<>();

                for (String cardNumber : cardNumbers) {
                    if (!cardNumber.equals("")) isIn.put(Integer.parseInt(cardNumber), true);
                }

                for (String myNumber : myNumbers) {
                    if (!myNumber.equals("") && isIn.getOrDefault(Integer.parseInt(myNumber), false)) {
                        if (linePoints == 0) {
                            linePoints = 1;
                        } else {
                            linePoints = linePoints * 2;
                        }
                    }
                }

                fullValue += linePoints;
            }
            reader.close();
            System.out.println(fullValue);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
