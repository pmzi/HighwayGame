//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sample.Models;

import javafx.scene.layout.GridPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.Controllers.SettingsController;
import sample.DesignObjects.Road;
import sample.DesignObjects.RoadBridge;
import sample.DesignObjects.RoadWay;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SettingsModel implements FullReadable, Writable {

    public static void save(SettingsController input) {


        JSONObject primaryObj = new JSONObject();

        primaryObj.put("persons", Integer.valueOf(input.getPersonsLength()));

        JSONObject ways = new JSONObject();
        ways.put("length",input.getRoadWays().size());
        ArrayList<JSONObject> wayItems = new ArrayList<>();
        for(RoadWay roadWay:input.getRoadWays()){
            JSONObject tempWay = new JSONObject();
            tempWay.put("x", roadWay.get().getTranslateX());
            wayItems.add(tempWay);
        }
        ways.put("items", wayItems);

        primaryObj.put("ways", ways);

        JSONObject bridges = new JSONObject();
        bridges.put("length",input.getRoadBridges().size());
        ArrayList<JSONObject> bridgeItems = new ArrayList<>();
        for(RoadBridge roadBridge:input.getRoadBridges()){
            JSONObject tempBridge = new JSONObject();
            tempBridge.put("x", roadBridge.get().getTranslateX());
            bridgeItems.add(tempBridge);
        }
        bridges.put("items", bridgeItems);

        primaryObj.put("bridges", bridges);

        JSONObject roads = new JSONObject();
        roads.put("length",input.getRoads().size());
        ArrayList<JSONObject> roadItems = new ArrayList<>();
        for(Road road:input.getRoads()){
            JSONObject tempRoad = new JSONObject();
            tempRoad.put("row", GridPane.getRowIndex(road.get()));
            tempRoad.put("randomize", road.getRandomize());
            tempRoad.put("speedStart", road.getSpeedStart());
            tempRoad.put("speedEnd", road.getSpeedEnd());
            roadItems.add(tempRoad);
        }
        roads.put("items", roadItems);

        primaryObj.put("roads", roads);

        Path path = Paths.get("src/sample/DB/settings.json", new String[0]);
        String newPath = path.toAbsolutePath().toString();

        try(FileWriter file = new FileWriter(newPath);) {

                file.write(primaryObj.toJSONString());
                file.flush();

        } catch (IOException e) {

        }

    }

    public static JSONObject read(){

        Path path = Paths.get("src/sample/DB/settings.json", new String[0]);
        String newPath = path.toAbsolutePath().toString();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(newPath));

            JSONObject jsonObject = (JSONObject) obj;

            return jsonObject;


        }catch (Exception e){
            return null;
        }

    }

}
