package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import sample.DesignObjects.*;
import sample.Models.SettingsModel;

import java.util.ArrayList;

/**
 * Created by pmzi on 7/6/2018.
 */
public class SettingsController {

    private int roads = 0;// should be doubled
    private int maxRoads = 4;
    private int persons = 0;
    private int maxPersons = 3;
    private int ways = 0;
    private int maxWays = 2;
    private int bridges = 0;
    private int maxBridges = 2;

    private int highwayHeight = 110;

    private boolean isGoingToRemove = false;

    private ArrayList<RoadWay> roadWays = new ArrayList<>();
    private ArrayList<RoadBridge> roadBridges = new ArrayList<>();

    @FXML
    private GridPane roadWrapper;
    @FXML
    private GridPane wrapper;

    public void addRoad(){

        if(!this.checkRoadNumber()){
            return;
        }

        // Let's add 1 to roads

        this.roads++;

        // Let's recalculate the highway height

        this.calculateHighwayHeight();

        // Let's repaint the roadSection

        this.repaintRoadSection();

        this.resizeWaysAndBridges();

    }

    public void addWay(){

        if(!this.checkWayNumber()){
            return;
        }

        this.ways++;

        RoadWay roadWay = new RoadWay(highwayHeight);

        this.roadWays.add(roadWay);

        this.paintWay(roadWay);

    }

    public void addBridge(){

        if(!this.checkBridgeNumbers()){
            return;
        }

        this.bridges++;

        RoadBridge roadBridge = new RoadBridge(highwayHeight);

        this.roadBridges.add(roadBridge);

        this.paintBridge(roadBridge);

    }

    public void addPerson(){

        if(!this.checkPersonsNumber()){
            return;
        }

        // Let's add to persons number

        this.persons++;

        // Let's repaint the road section so that new person will be shawn

        this.repaintRoadSection();

    }

    public void checkAndDelete(){

        if(this.isGoingToRemove){

            boolean deleted = false;

            for(int i =0;i<roadBridges.size();i++){
                if(roadBridges.get(i).isSelected()){
                    wrapper.getChildren().remove(roadBridges.get(i).get());
                    roadBridges.remove(i);

                    deleted = true;
                }
            }

            for(int i =0;i<roadWays.size();i++){
                if(roadWays.get(i).isSelected()){
                    wrapper.getChildren().remove(roadWays.get(i).get());
                    roadWays.remove(i);

                    deleted = true;
                }
            }

            if(deleted){
                isGoingToRemove = false;
            }

        }

    }

    public void listenForRemove(){

        this.unSelectAll();

        this.isGoingToRemove = true;
    }

    public void deletePerson(){

        if(this.persons == 0){
            return;
        }

        // Let's minus the roads

        this.persons--;

        // Let's repaint the roads

        this.repaintRoadSection();

    }

    public void deleteRoad(){

        if(this.roads == 0){
            return;
        }

        // Let's minus the roads

        this.roads--;

        // Let's recalculate the highway height

        this.calculateHighwayHeight();

        // Let's repaint the roadSection

        this.resizeWaysAndBridges();

        // Let's repaint the roads

        this.repaintRoadSection();

    }

    public void moveRight(){

        this.moveSelecteds(5);

    }

    public void moveLeft(){

        this.moveSelecteds(-5);

    }

    public void save(){

        SettingsModel.save(this);

    }

    public int getPersonsLength(){
        return this.persons;
    }

    // Private methods

    private boolean checkRoadNumber(){
        if(this.roads < this.maxRoads){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkWayNumber(){
        if(this.ways < this.maxWays){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkBridgeNumbers(){
        if(this.bridges < this.maxBridges){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkPersonsNumber(){
        if(this.persons < this.maxPersons){
            return true;
        }else{
            return false;
        }
    }

    private void repaintRoadSection(){
        // Let's clear all

        roadWrapper.getChildren().clear();

        // Let's add upward road

        for(int i=3;i>(3-this.roads);i--){

            Road road = new Road(i);

            roadWrapper.getChildren().add(road.get());

        }

        // Let's add downward road

        for(int i=5;i<(5+this.roads);i++){

            Road road = new Road(i);

            roadWrapper.getChildren().add(road.get());

        }

        // Let's add roadMiddleware

        RoadMiddleware roadMiddleware = new RoadMiddleware(4);
        roadWrapper.getChildren().add(roadMiddleware.get());

        // Let's add aside

        RoadAside roadAside = new RoadAside(this.roads+5,persons);
        roadWrapper.getChildren().add(roadAside.get());

        // We are done;)
    }

    private void paintBridge(RoadBridge roadBridge){

        wrapper.getChildren().add(roadBridge.get());

    }

    private void paintWay(RoadWay roadWay){

        wrapper.getChildren().add(roadWay.get());

    }

    private void resizeWaysAndBridges(){

        for(RoadBridge bridge:roadBridges){
            bridge.setMaxHeight(this.highwayHeight);
        }

        for(RoadWay way:roadWays){
            way.setMaxHeight(this.highwayHeight);
        }

    }

    private void calculateHighwayHeight(){
        this.highwayHeight = (this.roads * 2 * 52) + 10;
    }

    private void unSelectAll(){

        for(int i =0;i<roadBridges.size();i++){
            if(roadBridges.get(i).isSelected()){
                roadBridges.get(i).unSelect();
            }
        }

        for(int i =0;i<roadWays.size();i++){
            if(roadWays.get(i).isSelected()){
                roadWays.get(i).unSelect();
            }
        }

    }

    private void moveSelecteds(int pos){
        for(int i =0;i<roadBridges.size();i++){
            if(roadBridges.get(i).isSelected()){
                roadBridges.get(i).changePosition(pos);
            }
        }

        for(int i =0;i<roadWays.size();i++){
            if(roadWays.get(i).isSelected()){
                roadWays.get(i).changePosition(pos);
            }
        }
    }

}
