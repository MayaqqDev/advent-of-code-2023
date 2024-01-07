import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FifthDay {
    public static void run(String path) {
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);

            long lowestNumber = 0;

            String[] seedNumbers = new String[0];

            ArrayList<ResourceMap> seedToSoil = new ArrayList<>();
            ArrayList<ResourceMap> soilToFertilizer = new ArrayList<>();
            ArrayList<ResourceMap> fertilizerToWater = new ArrayList<>();
            ArrayList<ResourceMap> waterToLight = new ArrayList<>();
            ArrayList<ResourceMap> lightToTemperature = new ArrayList<>();
            ArrayList<ResourceMap> temperatureToHumidity = new ArrayList<>();
            ArrayList<ResourceMap> humidityToLocation = new ArrayList<>();


            ArrayList<String> lines = new ArrayList<>();
            while (reader.hasNextLine()) lines.add(reader.nextLine());

            int currentMap = 0;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.startsWith("seeds")) {
                    seedNumbers = line.replaceAll("seeds: ", "").split(" ");
                } else {
                    if (line.matches("^[A-Za-z].*")) {
                        currentMap += 1;
                        for (int j = i + 1; j < lines.size(); j++) {
                            String[] values = lines.get(j).split(" ");
                            if (lines.get(j).matches("^[A-Za-z].*") || lines.get(j).isBlank()) break;
                            long firstRange = Long.parseLong(values[0]);
                            long secondRange = Long.parseLong(values[1]);
                            long amount = Long.parseLong(values[2]);
                            ResourceMap map = new ResourceMap(firstRange, secondRange, amount);
                            switch (currentMap) {
                                case 1:
                                    seedToSoil.add(map);
                                    break;
                                case 2:
                                    soilToFertilizer.add(map);
                                    break;
                                case 3:
                                    fertilizerToWater.add(map);
                                    break;
                                case 4:
                                    waterToLight.add(map);
                                    break;
                                case 5:
                                    lightToTemperature.add(map);
                                    break;
                                case 6:
                                    temperatureToHumidity.add(map);
                                    break;
                                case 7:
                                    humidityToLocation.add(map);
                                    break;
                                default: break;
                            }
                        }
                    }
                }
            }

            ArrayList<ArrayList<ResourceMap>> resourceMaps = new ArrayList<>();
            resourceMaps.add(seedToSoil);
            resourceMaps.add(soilToFertilizer);
            resourceMaps.add(fertilizerToWater);
            resourceMaps.add(waterToLight);
            resourceMaps.add(lightToTemperature);
            resourceMaps.add(temperatureToHumidity);
            resourceMaps.add(humidityToLocation);

            for (String seedString : seedNumbers) {

                long location = Long.parseLong(seedString);

                for (ArrayList<ResourceMap> maps : resourceMaps) {
                    System.out.println(seedString + ": " + location);
                    for (ResourceMap map : maps) {
                        location = map.check(location);
                    }
                }
                if (lowestNumber == 0) {
                    lowestNumber = location;
                } else if (location < lowestNumber) {
                    lowestNumber = location;
                }
            }

            System.out.println(lowestNumber);

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static class ResourceMap {

        final long firstRange;
        final long secondRange;
        final long amount;

        public ResourceMap(long firstRange, long secondRange, long amount) {
            this.firstRange = firstRange;
            this.secondRange = secondRange;
            this.amount = amount;
        }

        public long check(long number) {
            if (number >= firstRange && number <= firstRange+amount) {
                long difference = number - firstRange;
                return secondRange + difference;
            } else {
                return number;
            }
        }
    }
}
