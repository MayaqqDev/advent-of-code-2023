import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SecondDay {
    public static void run(String path) {
        int summedIds = 0;

        HashMap<String, Integer> colorMax = new HashMap<>();

        colorMax.put("red", 12);
        colorMax.put("green", 13);
        colorMax.put("blue", 14);

        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine().replaceAll(" ", "").toLowerCase();
                int GameID = Integer.parseInt(data.split(":")[0].replace("game", ""));
                String[] sets = data.replaceAll("game([0-9]|[1-9][0-9]|100):", "").split(";");
                boolean allSetsFine = true;
                for (String set : sets) {
                    String[] subsets = set.split(",");
                    for (String subset : subsets) {
                        int amount = Integer.parseInt(subset.replaceAll("[^0-9]", ""));
                        String color = subset.replaceAll("[0-9]", "");

                        if (!(amount <= colorMax.get(color))) {
                            allSetsFine = false;
                        }
                    }
                }
                if (allSetsFine) {
                    summedIds += GameID;
                }
            }
            reader.close();
            System.out.println(summedIds);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
