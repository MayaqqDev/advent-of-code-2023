import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ThirdDay {
    public static void run(String path) {
        int fullValue = 0;
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            ArrayList<String> lines = new ArrayList<>();
            while (reader.hasNextLine()) lines.add(reader.nextLine());
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String previousLine = null;
                if (i != 0) {
                    previousLine = lines.get(i - 1);
                }
                String nextLine = null;
                if (i < 139) {
                    nextLine = lines.get(i + 1);
                }

                for (int j = 0; j < line.length(); j++) {
                    char ch = line.charAt(j);

                    boolean add = false;
                    String number = "";
                    int size = 0;

                    if (String.valueOf(ch).matches("[0-9]")) {
                        number += ch;
                        size++;
                        if (String.valueOf(customCharAt(line, j + 1)).matches("[0-9]")) {
                            number += line.charAt(j + 1);
                            size++;
                            if (String.valueOf(customCharAt(line, j + 2)).matches("[0-9]")) {
                                number += line.charAt(j + 2);
                                size++;
                            }
                        }
                    } else {
                        continue;
                    }

                    String regex = "^[^.0-9]$";

                    if (String.valueOf(customCharAt(line, j - 1)).matches(regex)) {
                        add = true;
                    }
                    if (String.valueOf(customCharAt(line, j + size)).matches(regex)) {
                        add = true;
                    }

                    for (int k = j-1; k < j + size + 1; k++) {
                        if (previousLine != null) {
                            if (String.valueOf(customCharAt(previousLine, k)).matches(regex)) {
                                add = true;
                            }
                        }
                        if (nextLine != null) {
                            if (String.valueOf(customCharAt(nextLine, k)).matches(regex)) {
                                add = true;
                            }
                        }
                    }

                    if (add && !number.isEmpty()) {
                        fullValue += Integer.parseInt(number);
                        System.out.println("Number " + number + " is correct");
                    }
                    j += size - 1;
                }
            }

            reader.close();
            System.out.println(fullValue);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static char customCharAt(String line, int number) {
        if (line == null) return '.';
        if (number < 0) return '.';
        if (number > 139) return '.';
        return line.charAt(number);
    }
}