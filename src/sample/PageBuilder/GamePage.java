package sample.PageBuilder;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.Controllers.GameController;
import sample.DesignObjects.*;
import sample.Models.SettingsModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by pmzi on 7/6/2018.
 */
public class GamePage extends BasePageBuilder {

    private ArrayList<Road> roadsElements = new ArrayList<>();
    private ArrayList<RoadWay> roadWays = new ArrayList<>();
    private ArrayList<RoadBridge> roadBridges = new ArrayList<>();
    private ArrayList<Car> carElements = new ArrayList<>();
    private ArrayList<Person> personElements = new ArrayList<>();
    private ArrayList<JSONArray> personMoves = new ArrayList<>();

    private int persons = 0;
    private int roads = 0;
    private int ways = 0;
    private int bridges = 0;

    public void show(){

        this.parse();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/game.fxml"));

            Parent root = (Parent) fxmlLoader.load();
            GameController controller = fxmlLoader.getController();

            controller.setBridges(this.bridges);
            controller.setPersons(this.persons);
            controller.setRoads(this.roads);
            controller.setWays(this.ways);
            controller.setRoadsElements(this.roadsElements);
            controller.setRoadWays(this.roadWays);
            controller.setRoadBridges(this.roadBridges);

            controller.insertData();

            Scene scene = new Scene(root, 1600, 520);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    System.out.println(event.getCode());
                    switch (event.getCode()) {
                        case UP:    controller.goUp();controller.personDidMove(); break;
                        case DOWN:  controller.goDown();controller.personDidMove(); break;
                        case LEFT:  controller.goLeft();controller.personDidMove(); break;
                        case RIGHT: controller.goRight();controller.personDidMove(); break;
                        case DIGIT1: controller.switchPersons(0); break;
                        case DIGIT2: controller.switchPersons(1); break;
                        case DIGIT3: controller.switchPersons(2); break;
                    }
                }
            });
            Stage stage = new Stage();
            stage.setTitle("Traffic Simulator");
            scene.getStylesheets().add("sample/assets/css/main.css");
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.setScene(scene);
            stage.show();

            controller.sceneDidMount();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadPage(JSONObject data){
        this.parseLoad(data);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/game.fxml"));

            Parent root = (Parent) fxmlLoader.load();
            GameController controller = fxmlLoader.getController();

            controller.setBridges(this.bridges);
            controller.setPersons(this.personElements.size());
            controller.setRoads(this.roads);
            controller.setWays(this.ways);
            controller.setRoadsElements(this.roadsElements);
            controller.setRoadWays(this.roadWays);
            controller.setRoadBridges(this.roadBridges);
            controller.setPersonElements(this.personElements);
            controller.setCarElements(this.carElements);

            controller.insertData();

            Scene scene = new Scene(root, 1600, 520);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    System.out.println(event.getCode());
                    switch (event.getCode()) {
                        case UP:    controller.goUp();controller.personDidMove(); break;
                        case DOWN:  controller.goDown();controller.personDidMove(); break;
                        case LEFT:  controller.goLeft();controller.personDidMove(); break;
                        case RIGHT: controller.goRight();controller.personDidMove(); break;
                        case DIGIT1: controller.switchPersons(0); break;
                        case DIGIT2: controller.switchPersons(1); break;
                        case DIGIT3: controller.switchPersons(2); break;
                    }
                }
            });
            Stage stage = new Stage();
            stage.setTitle("Traffic Simulator");
            scene.getStylesheets().add("sample/assets/css/main.css");
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.setScene(scene);
            stage.show();

            controller.sceneDidMount();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void parseLoad(JSONObject data){

        //

        JSONObject personsObjects = (JSONObject) data.get("persons");

        JSONArray personsItems = (JSONArray) personsObjects.get("items");

        Iterator p = personsItems.iterator();

        while(p.hasNext()){

            JSONObject personObject = (JSONObject) p.next();

            Person tempPerson = new Person();
            tempPerson.setPersonYPosition((int)(long)personObject.get("yPosition"));
            tempPerson.get().setTranslateX((int)(double)personObject.get("x"));
            tempPerson.get().setTranslateY((int)(double)personObject.get("y"));
            personElements.add(tempPerson);

        }

        //

        JSONObject roadsObject = (JSONObject) data.get("roads");

        this.roads = (int)(long)roadsObject.get("length");
        this.roads /= 2;

        JSONArray roadsItems = (JSONArray) roadsObject.get("items");

        Iterator i = roadsItems.iterator();

        while(i.hasNext()){

            JSONObject roadObject = (JSONObject) i.next();

            System.out.println(roadObject);

            Road tempRoad = new Road((int) (long) roadObject.get("row"), false);
            tempRoad.setRandomize((int) (long) roadObject.get("randomize"));
            tempRoad.setSpeedStart((int) (long) roadObject.get("speedStart"));
            tempRoad.setSpeedEnd((int) (long) roadObject.get("speedEnd"));

            roadsElements.add(tempRoad);

        }

        //

        JSONObject waysObjects = (JSONObject) data.get("ways");

        JSONArray waysItems = (JSONArray) waysObjects.get("items");

        this.ways = (int) (long) waysObjects.get("length");

        Iterator j = waysItems.iterator();

        while(j.hasNext()){

            JSONObject wayObject = (JSONObject) j.next();

            RoadWay tempRoadWay = new RoadWay(this.calculateHighwayHeight());
            tempRoadWay.changePosition((int)(double)wayObject.get("x"));

            roadWays.add(tempRoadWay);

        }

        //

        JSONObject bridgesObjects = (JSONObject) data.get("bridges");

        JSONArray bridgesItems = (JSONArray) bridgesObjects.get("items");

        this.bridges = (int) (long) bridgesObjects.get("length");

        Iterator k = bridgesItems.iterator();

        while(k.hasNext()){

            JSONObject bridgeObject = (JSONObject) k.next();

            RoadBridge tempRoadBridge = new RoadBridge(this.calculateHighwayHeight());
            tempRoadBridge.changePosition((int)(double)bridgeObject.get("x"));

            roadBridges.add(tempRoadBridge);

        }

        //

        JSONObject carsObjects = (JSONObject) data.get("cars");

        JSONArray carsItems = (JSONArray) carsObjects.get("items");

        Iterator c = carsItems.iterator();

        while(c.hasNext()){

            JSONObject carObject = (JSONObject) c.next();

            Car tempCar = new Car(10,50,(int)(long)carObject.get("y"),(int)(long)carObject.get("direction"),(int)(long)carObject.get("creationTime"));
            tempCar.setPrimarySpeed((int)(long)carObject.get("speed"));
            tempCar.setInitiationX((int)(long)carObject.get("x"));
            carElements.add(tempCar);

        }

        // We got the data!-:)
    }

    private void parse(){
        JSONObject settings = SettingsModel.read();

        this.persons = (int) (long) settings.get("persons");

        JSONObject roadsObject = (JSONObject) settings.get("roads");

        this.roads = (int)(long)roadsObject.get("length");
        this.roads /= 2;

        JSONArray roadsItems = (JSONArray) roadsObject.get("items");

        Iterator i = roadsItems.iterator();

        while(i.hasNext()){

            JSONObject roadObject = (JSONObject) i.next();

            System.out.println(roadObject);

            Road tempRoad = new Road((int) (long) roadObject.get("row"), false);
            tempRoad.setRandomize((int) (long) roadObject.get("randomize"));
            tempRoad.setSpeedStart((int) (long) roadObject.get("speedStart"));
            tempRoad.setSpeedEnd((int) (long) roadObject.get("speedEnd"));

            roadsElements.add(tempRoad);

        }

        //

        JSONObject waysObjects = (JSONObject) settings.get("ways");

        JSONArray waysItems = (JSONArray) waysObjects.get("items");

        this.ways = (int) (long) waysObjects.get("length");

        Iterator j = waysItems.iterator();

        while(j.hasNext()){

            JSONObject wayObject = (JSONObject) j.next();

            RoadWay tempRoadWay = new RoadWay(this.calculateHighwayHeight());
            tempRoadWay.changePosition((int)(double)wayObject.get("x"));

            roadWays.add(tempRoadWay);

        }

        //

        JSONObject bridgesObjects = (JSONObject) settings.get("bridges");

        JSONArray bridgesItems = (JSONArray) bridgesObjects.get("items");

        this.bridges = (int) (long) bridgesObjects.get("length");

        Iterator k = bridgesItems.iterator();

        while(k.hasNext()){

            JSONObject bridgeObject = (JSONObject) k.next();

            RoadBridge tempRoadBridge = new RoadBridge(this.calculateHighwayHeight());
            tempRoadBridge.changePosition((int)(double)bridgeObject.get("x"));

            roadBridges.add(tempRoadBridge);

        }

        // We got the data!-:)

    }

    public void loadReply(JSONObject data){
        this.parseReply(data);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/game.fxml"));

            Parent root = (Parent) fxmlLoader.load();
            GameController controller = fxmlLoader.getController();

            controller.setBridges(this.bridges);
            controller.setPersons(this.personElements.size());
            controller.setRoads(this.roads);
            controller.setWays(this.ways);
            controller.setRoadsElements(this.roadsElements);
            controller.setRoadWays(this.roadWays);
            controller.setRoadBridges(this.roadBridges);
            controller.setPersonElements(this.personElements);
            controller.setCarElements(this.carElements);
            controller.setReplyMode(true);
            controller.setPersonMovements(this.personMoves);

            controller.insertData();

            Scene scene = new Scene(root, 1600, 520);

            Stage stage = new Stage();
            stage.setTitle("Traffic Simulator");
            scene.getStylesheets().add("sample/assets/css/main.css");
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.setScene(scene);
            stage.show();

            controller.sceneDidMount();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void parseReply(JSONObject data){

        JSONArray personMoves = (JSONArray) data.get("personMovements");
        this.personMoves = (ArrayList<JSONArray>) personMoves;

        //

        JSONObject personsObjects = (JSONObject) data.get("persons");

        JSONArray personsItems = (JSONArray) personsObjects.get("items");

        Iterator p = personsItems.iterator();

        while(p.hasNext()){

            JSONObject personObject = (JSONObject) p.next();

            Person tempPerson = new Person();
            tempPerson.setPersonYPosition((int)(long)personObject.get("yPosition"));
            tempPerson.get().setTranslateX((int)(double)personObject.get("x"));
            tempPerson.get().setTranslateY((int)(double)personObject.get("y"));
            personElements.add(tempPerson);

        }

        //

        JSONObject roadsObject = (JSONObject) data.get("roads");

        this.roads = (int)(long)roadsObject.get("length");
        this.roads /= 2;

        JSONArray roadsItems = (JSONArray) roadsObject.get("items");

        Iterator i = roadsItems.iterator();

        while(i.hasNext()){

            JSONObject roadObject = (JSONObject) i.next();

            System.out.println(roadObject);

            Road tempRoad = new Road((int) (long) roadObject.get("row"), false);
            tempRoad.setRandomize((int) (long) roadObject.get("randomize"));
            tempRoad.setSpeedStart((int) (long) roadObject.get("speedStart"));
            tempRoad.setSpeedEnd((int) (long) roadObject.get("speedEnd"));

            roadsElements.add(tempRoad);

        }

        //

        JSONObject waysObjects = (JSONObject) data.get("ways");

        JSONArray waysItems = (JSONArray) waysObjects.get("items");

        this.ways = (int) (long) waysObjects.get("length");

        Iterator j = waysItems.iterator();

        while(j.hasNext()){

            JSONObject wayObject = (JSONObject) j.next();

            RoadWay tempRoadWay = new RoadWay(this.calculateHighwayHeight());
            tempRoadWay.changePosition((int)(double)wayObject.get("x"));

            roadWays.add(tempRoadWay);

        }

        //

        JSONObject bridgesObjects = (JSONObject) data.get("bridges");

        JSONArray bridgesItems = (JSONArray) bridgesObjects.get("items");

        this.bridges = (int) (long) bridgesObjects.get("length");

        Iterator k = bridgesItems.iterator();

        while(k.hasNext()){

            JSONObject bridgeObject = (JSONObject) k.next();

            RoadBridge tempRoadBridge = new RoadBridge(this.calculateHighwayHeight());
            tempRoadBridge.changePosition((int)(double)bridgeObject.get("x"));

            roadBridges.add(tempRoadBridge);

        }

        //

        JSONObject carsObjects = (JSONObject) data.get("cars");

        JSONArray carsItems = (JSONArray) carsObjects.get("items");

        Iterator c = carsItems.iterator();

        while(c.hasNext()){

            JSONObject carObject = (JSONObject) c.next();

            Car tempCar = new Car(10,50,(int)(long)carObject.get("y"),(int)(long)carObject.get("direction"),(int)(long)carObject.get("creationTime"));
            tempCar.setPrimarySpeed((int)(long)carObject.get("speed"));
            tempCar.setInitiationX((int)(double)carObject.get("x"));
            carElements.add(tempCar);

        }

        // We got the data!-:)
    }

    private int calculateHighwayHeight(){
        return (this.roads * 2 * 52) + 10;
    }


}
