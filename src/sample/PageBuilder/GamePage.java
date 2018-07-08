package sample.PageBuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.DesignObjects.Road;
import sample.DesignObjects.RoadBridge;
import sample.DesignObjects.RoadWay;
import sample.Models.SettingsModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by pmzi on 7/6/2018.
 */
public class GamePage {

    private ArrayList<Road> roadsElements = new ArrayList<>();
    private ArrayList<RoadWay> roadWays = new ArrayList<>();
    private ArrayList<RoadBridge> roadBridges = new ArrayList<>();

    private int persons = 0;
    private int roads = 0;
    private int ways = 0;
    private int bridges = 0;

    public static void show(){
        GamePage gamePage = new GamePage();
        gamePage.showPage();
    }

    public void showPage(){

        this.parse();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/game.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1600, 520);
            Stage stage = new Stage();
            stage.setTitle("Traffic Simulator");
            scene.getStylesheets().add("sample/assets/css/main.css");
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
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

            Road tempRoad = new Road((int) (long) roadObject.get("row"));
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

    private int calculateHighwayHeight(){
        return (this.roads * 2 * 52) + 10;
    }


}
