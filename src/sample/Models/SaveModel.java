package sample.Models;

import javafx.scene.layout.GridPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.Controllers.BaseController;
import sample.Controllers.GameController;
import sample.DesignObjects.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by pmzi on 7/8/2018.
 */
public class SaveModel implements Writable, Readable, FullReadable {

    public static void save(BaseController input){

        String name = ((GameController) input).getSavename();

        JSONArray savedData = read();

        JSONObject primaryObj = new JSONObject();

        primaryObj.put("name",name);

        JSONObject persons = new JSONObject();
        persons.put("length",((GameController) input).getPersons().size());
        ArrayList<JSONObject> personItems = new ArrayList<>();
        for(Person person:((GameController) input).getPersons()){
            JSONObject tempPerson = new JSONObject();
            tempPerson.put("x", person.get().getTranslateX());
            tempPerson.put("yPosition", person.getPersonYPosition());
            tempPerson.put("y", person.get().getTranslateY());
            personItems.add(tempPerson);
        }
        persons.put("items", personItems);

        primaryObj.put("persons", persons);

        //

        JSONObject ways = new JSONObject();
        ways.put("length",((GameController) input).getRoadWays().size());
        ArrayList<JSONObject> wayItems = new ArrayList<>();
        for(RoadWay roadWay:((GameController) input).getRoadWays()){
            JSONObject tempWay = new JSONObject();
            tempWay.put("x", roadWay.get().getTranslateX());
            wayItems.add(tempWay);
        }
        ways.put("items", wayItems);

        primaryObj.put("ways", ways);

        JSONObject bridges = new JSONObject();
        bridges.put("length",((GameController) input).getRoadBridges().size());
        ArrayList<JSONObject> bridgeItems = new ArrayList<>();
        for(RoadBridge roadBridge:((GameController) input).getRoadBridges()){
            JSONObject tempBridge = new JSONObject();
            tempBridge.put("x", roadBridge.get().getTranslateX());
            bridgeItems.add(tempBridge);
        }
        bridges.put("items", bridgeItems);

        primaryObj.put("bridges", bridges);

        JSONObject roads = new JSONObject();
        roads.put("length",((GameController) input).getRoads().size());
        ArrayList<JSONObject> roadItems = new ArrayList<>();
        for(Road road:((GameController) input).getRoads()){
            JSONObject tempRoad = new JSONObject();
            tempRoad.put("row", GridPane.getRowIndex(road.get()));
            tempRoad.put("randomize", road.getRandomize());
            tempRoad.put("speedStart", road.getSpeedStart());
            tempRoad.put("speedEnd", road.getSpeedEnd());
            roadItems.add(tempRoad);
        }
        roads.put("items", roadItems);

        primaryObj.put("roads", roads);

        JSONObject cars = new JSONObject();
        cars.put("length",((GameController) input).getCars().size());
        ArrayList<JSONObject> carItems = new ArrayList<>();
        for(Car car:((GameController) input).getCars()){
            JSONObject tempCar = new JSONObject();
            tempCar.put("x", car.getPosition());
            tempCar.put("y", car.getRoadNumber());
            tempCar.put("direction", car.getDirection());
            tempCar.put("speed", car.getPrimarySpeed());
            tempCar.put("creationTime", car.getCreationTime());
            carItems.add(tempCar);
        }
        cars.put("items", carItems);

        primaryObj.put("cars", cars);

        primaryObj.put("score", ((GameController) input).getScore());

        primaryObj.put("timePassed", ((GameController) input).getTimePassed());

        savedData.add(primaryObj);

        Path path = Paths.get("src/sample/DB/saves.json", new String[0]);
        String newPath = path.toAbsolutePath().toString();

        try(FileWriter file = new FileWriter(newPath);) {

            file.write(savedData.toJSONString());
            file.flush();

        } catch (IOException e) {

        }

    }

    public static JSONArray read(){
        Path path = Paths.get("src/sample/DB/saves.json", new String[0]);
        String newPath = path.toAbsolutePath().toString();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(newPath));

            JSONArray savedData = (JSONArray) obj;

            return savedData;


        }catch (Exception e){
            return null;
        }
    }

    public static JSONObject getSpecific(String name){
        Path path = Paths.get("src/sample/DB/saves.json", new String[0]);
        String newPath = path.toAbsolutePath().toString();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(newPath));

            JSONArray savedData = (JSONArray) obj;

            for(int i = 0;i<savedData.size();i++){

                JSONObject save = (JSONObject) savedData.get(i);
                if(name.equals((String) save.get("name"))){
                    return save;
                }

            }

            return null;


        }catch (Exception e){
            return null;
        }
    }

    private String name;
    private int score;
    private int timePassed;

    public SaveModel(String name, int score, int timePassed){
        this.name = name;
        this.score = score;
        this.timePassed = timePassed;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }

    public int getTimePassed(){
        return this.timePassed;
    }

}
