package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sample.DesignObjects.Person;
import sample.DesignObjects.Road;
import sample.DesignObjects.RoadBridge;
import sample.DesignObjects.RoadWay;
import sample.Helpers.WindowHelper;
import sample.Models.SettingsModel;
import sample.PageBuilder.GamePage;

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

    private ArrayList<Road> roadsElements = new ArrayList<>();
    private ArrayList<RoadWay> roadWays = new ArrayList<>();
    private ArrayList<RoadBridge> roadBridges = new ArrayList<>();

    @FXML
    private GridPane roadWrapper;
    @FXML
    private GridPane wrapper;
    @FXML
    private HBox roadAside;

    public void addRoad(){

        if(!this.checkRoadNumber()){
            return;
        }

        // Let's add 1 to roads

        this.roads++;

        // Let's recalculate the highway height

        this.calculateHighwayHeight();

        // Let's create 2 new roads

        Road road1 = new Road(this.maxRoads - this.roads, true);
        Road road2 = new Road(this.maxRoads + this.roads, true);

        this.roadsElements.add(road1);
        this.roadsElements.add(road2);

        // Let's paint them

        this.paintRoads(road1, road2);

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

        this.repaintRoadAside();

    }

    public void checkAndDelete(){

        if(this.isGoingToRemove){

            boolean deleted = false;

            for(int i =0;i<roadBridges.size();i++){
                if(roadBridges.get(i).isSelected()){
                    wrapper.getChildren().remove(roadBridges.get(i).get());
                    roadBridges.remove(i);
                    bridges--;
                    deleted = true;
                }
            }

            for(int i =0;i<roadWays.size();i++){
                if(roadWays.get(i).isSelected()){
                    wrapper.getChildren().remove(roadWays.get(i).get());
                    roadWays.remove(i);
                    ways--;
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

        this.repaintRoadAside();

    }

    public void deleteRoad(){

        if(this.roads == 0){
            return;
        }

        // Let's minus the roads

        this.roads--;

        // Let's get the poor roads

        int firstIndex = this.roadsElements.size()-1;
        int secondIndex = this.roadsElements.size()-2;

        Road road1 = this.roadsElements.get(firstIndex);
        Road road2 = this.roadsElements.get(secondIndex);

        this.roadsElements.remove(firstIndex);
        this.roadsElements.remove(secondIndex);

        System.out.println(firstIndex);
        System.out.println(secondIndex);
        this.removeRoads(road1, road2);

        // Let's recalculate the highway height

        this.calculateHighwayHeight();

        // Let's repaint the roadSection

        this.resizeWaysAndBridges();

    }

    public void moveRight(){

        this.moveSelecteds(5);

    }

    public void moveLeft(){

        this.moveSelecteds(-5);

    }

    public void save(ActionEvent e){

        SettingsModel.save(this);

        WindowHelper.hideCurrent(e);

        GamePage.show();

    }

    public int getPersonsLength(){
        return this.persons;
    }

    public ArrayList<RoadWay> getRoadWays(){
        return this.roadWays;
    }

    public ArrayList<RoadBridge> getRoadBridges(){
        return this.roadBridges;
    }

    public ArrayList<Road> getRoads(){
        return this.roadsElements;
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

    private void repaintRoadAside(){

        // Let's clear roadAside

        this.roadAside.getChildren().clear();

        // Let's add aside

        for(int i=0;i<persons;i++){
            Person person = new Person();

            this.roadAside.getChildren().add(person.get());
        }

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

    private void paintRoads(Road road1, Road road2){

        roadWrapper.getChildren().add(road1.get());
        roadWrapper.getChildren().add(road2.get());

    }

    private void removeRoads(Road road1, Road road2){

        roadWrapper.getChildren().remove(road1.get());
        roadWrapper.getChildren().remove(road2.get());

    }

}
