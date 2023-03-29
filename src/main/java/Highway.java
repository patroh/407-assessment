import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Highway {

    private static final double TOLL_PRICE_PER_KM = 0.25;
    public JSONObject locations;
    public HashMap<String, Integer> interchangeNameMap = new HashMap<>();

    Highway() {
        this.locations = readJsonFile("interchanges.json").getJSONObject("locations");
        mapInterchangeNameWithId();
    }

    private JSONObject readJsonFile(String pathToFile) {
        File file = new File(pathToFile);
        String jsonString = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                jsonString += scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new JSONObject(jsonString);
    }

    private void mapInterchangeNameWithId() {
        // We map the interchange name with ID so it become easier to lookup for the ID when user pass in name to test
        for (String id : locations.keySet()) {
            interchangeNameMap.put(locations.getJSONObject(id).getString("name"), Integer.parseInt(id));
        }
    }

    private double calculateDistanceBetweenTwoPoints(int startingPointId, int endPointId) {
        double distance = 0;
        JSONArray routes = locations.getJSONObject("" + startingPointId).getJSONArray("routes");

        for (int i = 0; i < routes.length(); i++) {
            JSONObject route = routes.getJSONObject(i);
            if (route.getInt("toId") == endPointId) {
                distance += route.getDouble("distance");
                return distance;
            }
        }
        return distance;
    }

    public double calculateDistanceBetweenTwoInterchange(String startingInterchange, String endInterchange) {
        int startId = interchangeNameMap.get(startingInterchange);
        int endId = interchangeNameMap.get(endInterchange);

        double distance = 0;
        if (endId < startId) {
            // If we are starting from bottom and going top
            for (int i = startId; i > endId; i--) {
                // Iterates and gets distance between every 2 points between start-end interchange
                distance += calculateDistanceBetweenTwoPoints(i, i - 1);
            }
        } else {
            // If we are starting from top and going bottom
            for (int i = startId; i < endId; i++) {
                // Iterates and gets distance between every 2 points between start-end interchange
                distance += calculateDistanceBetweenTwoPoints(i, i + 1);
            }
        }

        return distance;
    }


    public double calculatePriceForToll(double distance) {
        return distance * TOLL_PRICE_PER_KM;
    }
}
