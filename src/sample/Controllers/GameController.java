package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.DesignObjects.Road;
import sample.DesignObjects.RoadAside;
import sample.DesignObjects.RoadBridge;
import sample.DesignObjects.RoadWay;
import sample.PageBuilder.SaveModal;

import java.util.ArrayList;
import java.util.Optional;


public class GameController {

    private ArrayList<Road> roadsElements = new ArrayList<>();
    private ArrayList<RoadWay> roadWays = new ArrayList<>();
    private ArrayList<RoadBridge> roadBridges = new ArrayList<>();

    private int persons = 0;
    private int roads = 0;
    private int ways = 0;
    private int bridges = 0;

    public void setRoadsElements(ArrayList<Road> roadsElements){
        this.roadsElements = roadsElements;
    }
    public void setRoadWays(ArrayList<RoadWay> roadWays){
        this.roadWays = roadWays;
    }
    public void setRoadBridges(ArrayList<RoadBridge> roadBridges){
        this.roadBridges = roadBridges;
    }
    public void setPersons(int persons){
        this.persons = persons;
    }
    public void setRoads(int roads){
        this.roads = roads;
    }
    public void setWays(int ways){
        this.ways = ways;
    }
    public void setBridges(int bridges){
        this.bridges = bridges;
    }


    @FXML
    private Button exitIcon;
    @FXML
    private GridPane roadWrapper;
    @FXML
    private GridPane wrapper;

    @FXML
    public void showSave(){

        SaveModal saveModal = new SaveModal();
        saveModal.show();

    }

    @FXML
    public void exit(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            Platform.exit();
        }

    }

    public void insertData(){

        roadWrapper.getChildren().clear();
        roadWrapper.getRowConstraints().clear();

        // Let's calculate the road constraints

        double roadConstraints = 88 / (this.roads *2);

        // Road aside upper constraints

        RowConstraints roadAside1Constraint = new RowConstraints();
        roadAside1Constraint.setPercentHeight(5);
        roadWrapper.getRowConstraints().add(roadAside1Constraint);

        for(int i =0;i<this.roads;i++){
            RowConstraints tempRoadConst = new RowConstraints();
            tempRoadConst.setPercentHeight(roadConstraints);
            roadWrapper.getRowConstraints().add(tempRoadConst);
        }

        // road middleware constraints

        RowConstraints roadMiddlewareConstraints = new RowConstraints();
        roadMiddlewareConstraints.setPercentHeight(2);
        roadWrapper.getRowConstraints().add(roadMiddlewareConstraints);

        // continue roads

        for(int i =0;i<this.roads;i++){
            RowConstraints tempRoadConst = new RowConstraints();
            tempRoadConst.setPercentHeight(roadConstraints);
            roadWrapper.getRowConstraints().add(tempRoadConst);
        }

        // road aside down const

        RowConstraints roadAside2Constraint = new RowConstraints();
        roadAside2Constraint.setPercentHeight(5);
        roadWrapper.getRowConstraints().add(roadAside2Constraint);

        // Let's add actual roads
        int k =0;
        int j =0;
        for(int i = 0;i < this.roads*2;i++){
            if(this.roadsElements.get(i).getRowIndex() < 4){
                // upper ones
                roadsElements.get(i).setRowIndex(this.roads - k);
                roadWrapper.getChildren().add(roadsElements.get(i).get());
                k++;
            }else{
                roadsElements.get(i).setRowIndex(this.roads + 2 + j);
                roadWrapper.getChildren().add(roadsElements.get(i).get());
                j++;
            }
        }

        // Let's append roadAsides

        RoadAside roadAside1 = new RoadAside(0,0);
        RoadAside roadAside2 = new RoadAside((this.roads * 2) + 2, this.persons);

        roadWrapper.getChildren().addAll(roadAside1.get(),roadAside2.get());

        // Let's append roadWays

        for(int i =0;i < this.ways;i++){
            this.roadWays.get(i).setColumnIndex(0);
            this.roadWays.get(i).setRowIndex(1);
            this.roadWays.get(i).setStyleClass("roadWayGamePage");
            this.roadWays.get(i).setMaxWidth(70);
            this.roadWays.get(i).setMaxHeight(430);
            wrapper.getChildren().add(this.roadWays.get(i).get());
        }

        // Let's append roadBridges

        for(int i =0;i < this.bridges;i++){
            this.roadBridges.get(i).setColumnIndex(0);
            this.roadBridges.get(i).setRowIndex(1);
            this.roadBridges.get(i).setStyleClass("roadBridgeGamePage");
            this.roadBridges.get(i).setMaxWidth(70);
            this.roadBridges.get(i).setMaxHeight(430);
            wrapper.getChildren().add(this.roadBridges.get(i).get());
        }

        // We are good to go!

    }

}
