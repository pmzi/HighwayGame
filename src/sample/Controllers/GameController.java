package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import sample.DesignObjects.*;
import sample.Models.RankingModel;
import sample.PageBuilder.FinishModal;
import sample.PageBuilder.LostModal;
import sample.PageBuilder.MainPage;
import sample.PageBuilder.SaveModal;

import java.util.*;


public class GameController {

    private ArrayList<Road> roadsElements = new ArrayList<>();
    private ArrayList<RoadWay> roadWays = new ArrayList<>();
    private ArrayList<RoadBridge> roadBridges = new ArrayList<>();
    private ArrayList<Person> personElements = new ArrayList<>();

    private ArrayList<Car> carElements = new ArrayList<>();

    private RoadAside roadAsideDown;
    private int roadAsideHeight = 0;

    private RoadMiddleware roadMiddleware;
    private int roadMiddlewareHeight;

    private int persons = 0;
    private int roads = 0;
    private int ways = 0;
    private int bridges = 0;

    private int score = 0;
    private int timePassed = 1;

    private int selectedPerson = 0;
    private int roadHeight = 0;

    private boolean isPaused = false;

    private Timer gameControllerTimer;
    private Timer carGeneratorTimer;
    private Timer timePassTimer;

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
    private Label scoreText;
    @FXML
    private Label timePassedText;
    @FXML
    private Button pauseIcon;

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

        // Let's add road middleware

        RoadMiddleware roadMiddleware = new RoadMiddleware(this.roads+1);
        roadWrapper.getChildren().add(roadMiddleware.get());

        // Let's append roadAsides

        RoadAside roadAside1 = new RoadAside(0,0);
        RoadAside roadAside2 = new RoadAside((this.roads * 2) + 2, this.persons);

        this.personElements = roadAside2.getPersons();

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

        this.roadAsideDown = roadAside2;

        this.roadMiddleware = roadMiddleware;

        // We are good to go!

    }

    public void pauseOrRun(){
        this.isPaused = !this.isPaused;

        if(this.isPaused){
            pauseIcon.getStyleClass().clear();
            pauseIcon.getStyleClass().add("runIcon");
        }else{
            pauseIcon.getStyleClass().clear();
            pauseIcon.getStyleClass().add("pauseIcon");
        }
        pauseIcon.getStyleClass().add("gameIconicButtons");
    }

    private void setScore(int score){
        this.score += score;
        //
        scoreText.setText(Integer.toString(this.score));
    }

    private void changeTime(){
        this.timePassed++;

        String textOfTime = new String();

        String seconds = Integer.toString(this.timePassed%60);

        String minutes = Integer.toString(this.timePassed / 60);

        if(minutes.length() == 1){
            minutes = "0"+minutes;
        }
        if(seconds.length() == 1){
            seconds = "0"+seconds;
        }

        textOfTime = minutes+":"+seconds;

        timePassedText.setText(textOfTime);
    }

    public void sceneDidMount(){
        this.roadHeight = (int) this.roadsElements.get(0).get().getLayoutBounds().getHeight();
        this.roadAsideHeight = (int) this.roadAsideDown.get().getLayoutBounds().getHeight();
        this.roadMiddlewareHeight = (int) this.roadMiddleware.get().getLayoutBounds().getHeight();

        this.initTimePassedTimer();

        this.initCarGenerator();

        this.initCarController();

    }

    public void switchPersons(int number){

        if(number >= 0 && number <= this.persons){
            this.selectedPerson = number;
        }

    }

    public void goUp(){

        if(isPaused){
            return;
        }

        if(this.personElements.get(this.selectedPerson).getPersonYPosition() == 0){
            this.personElements.get(this.selectedPerson).changePosition(-1, (int) -1* ((roadHeight/2) + (this.roadAsideHeight / 2)));
        }else if(this.personElements.get(this.selectedPerson).getPersonYPosition() == this.roads){
            this.personElements.get(this.selectedPerson).changePosition(-1, (int) -1* (roadHeight + this.roadMiddlewareHeight));
        }
        else{
            this.personElements.get(this.selectedPerson).changePosition(-1, (int) -1 * roadHeight);
        }
        this.personElements.get(this.selectedPerson).setPersonYPosition(+1);

    }

    public void goDown(){

        if(isPaused){
            return;
        }

        if(this.personElements.get(this.selectedPerson).getPersonYPosition() == 1){
            this.personElements.get(this.selectedPerson).changePosition(-1, (int) +1* ((roadHeight/2) + (this.roadAsideHeight / 2)));
        }else if(this.personElements.get(this.selectedPerson).getPersonYPosition() == this.roads + 1){
            this.personElements.get(this.selectedPerson).changePosition(-1, (int) +1* (roadHeight + this.roadMiddlewareHeight));
        }
        else{
            this.personElements.get(this.selectedPerson).changePosition(-1, (int) +1 * roadHeight);
        }
        this.personElements.get(this.selectedPerson).setPersonYPosition(-1);

    }

    public void goRight(){

        if(isPaused){
            return;
        }

        if(!onBridge(this.personElements.get(this.selectedPerson))){
            this.personElements.get(this.selectedPerson).changePosition(10, -1);
        }
    }

    public void goLeft(){

        if(isPaused){
            return;
        }

        if(!onBridge(this.personElements.get(this.selectedPerson))){
            this.personElements.get(this.selectedPerson).changePosition(-10, -1);
        }
    }

    public void personDidMove(){
        int personsInAside = 0;
        for(Person person:personElements){
            if(person.getPersonYPosition() == this.roads*2 + 1){

                if(!person.getDidPass()){
                    person.setDidPass(true);
                    if(onBridge(person)){
                        this.setScore(1000 / this.timePassed);
                    }else{
                        this.setScore(5000 / this.timePassed);
                    }

                }

                personsInAside++;
            }
        }
        if(personsInAside == this.persons){
            this.stopAll();
            FinishModalController finisherController = this.showWin();
            String saveName = finisherController.saveText.getText();

            RankingModel.save(saveName, this.score, this.timePassed);

            Stage stage = (Stage) wrapper.getScene().getWindow();
            stage.close();

            MainPage mainPage = new MainPage();
            mainPage.show();

        }
    }

    private void initTimePassedTimer(){
        this.timePassTimer = new Timer();
        timePassTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(isPaused){
                        return;
                    }
                    changeTime();
                });
            }
        }, 1000, 1000);
    }

    private void initCarGenerator(){
        this.carGeneratorTimer = new Timer();
        carGeneratorTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    if(isPaused){
                        return;
                    }

                    int roadNumber = new Random().nextInt(roads*2) + 1;
                    int direction = 0;
                    if(roadNumber < roads +1){
                        direction = 1;
                    }

                    Car car = new Car(30,50,roadNumber, direction);

                    carElements.add(car);

                    for(Road roadElement:roadsElements){
                        if(roadElement.getRowIndex() < roads+1){
                            if(roadElement.getRowIndex() == roadNumber){
                                roadElement.get().getChildren().add(car.get());
                            }
                        }else{
                            if(roadElement.getRowIndex() - 1 == roadNumber){
                                roadElement.get().getChildren().add(car.get());
                            }
                        }
                    }

                });
            }
        }, 100, 5000);
    }

    private void initCarController(){

        this.gameControllerTimer = new Timer();
        gameControllerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    if(isPaused){
                        return;
                    }

                    for(Car carElement:carElements){
                        carElement.move();
                    }

                    // check car front

                    for(Car carElement:carElements){
                        for(Car singleCar:carElements){

                            if(carElement == singleCar){
                                continue;
                            }

                            if(carElement.getRoadNumber() == singleCar.getRoadNumber()){
                                if(carElement.getDirection() == 1){// to left
                                    if(singleCar.getPosition() - carElement.getPosition() < 100 && singleCar.getPosition() - carElement.getPosition() > 0){
                                        // do sth
                                        System.out.println(singleCar.getPosition() - carElement.getPosition());
                                        if(carElement.getRoadNumber() != 1){
                                            boolean hasSpace = true;
                                            for(Car upperCar:carElements){
                                                if(singleCar.getRoadNumber() - upperCar.getRoadNumber() != 1){
                                                    continue;
                                                }

                                                if(singleCar.getPosition() - upperCar.getPosition() < 100 && singleCar.getPosition() - upperCar.getPosition() > 0){
                                                    hasSpace = false;
                                                    break;
                                                }

                                            }
                                            if(hasSpace){
                                                int translateX = (int) singleCar.get().getTranslateX();
                                                removeCar(singleCar);
                                                addCarToRow(singleCar, singleCar.getRoadNumber()-1, translateX);
                                                singleCar.goUp();
//                                                singleCar.goUp(roadHeight);
                                            }else{
                                                singleCar.setCurrSpeed(carElement.getCurrSpeed());
                                            }

                                        }else{
                                            singleCar.setCurrSpeed(carElement.getCurrSpeed());
                                        }
                                    }
                                }else{// to right
                                    if(carElement.getPosition() - singleCar.getPosition() < 100 && carElement.getPosition() - singleCar.getPosition()>0){
                                        // do sth
                                        System.out.println(carElement.getPosition() - singleCar.getPosition());
                                        if(carElement.getRoadNumber() != roads+1){

                                            boolean hasSpace = true;
                                            for(Car upperCar:carElements){
                                                if(singleCar.getRoadNumber() - upperCar.getRoadNumber() != 1){
                                                    continue;
                                                }

                                                if(singleCar.getPosition() - upperCar.getPosition() < 100 && singleCar.getPosition() - upperCar.getPosition() > 0){
                                                    hasSpace = false;
                                                    break;
                                                }

                                            }
                                            if(hasSpace){
                                                int translateX = (int) singleCar.get().getTranslateX();
                                                removeCar(singleCar);
                                                addCarToRow(singleCar, singleCar.getRoadNumber()-1,translateX);
                                                singleCar.goUp();
                                            }else{
                                                singleCar.setCurrSpeed(carElement.getCurrSpeed());
                                            }

                                        }else{
                                            singleCar.setCurrSpeed(carElement.getCurrSpeed());
                                        }
                                    }
                                }
                            }

                        }


                        // checking for way nearby

                            for(RoadWay roadWay:roadWays){
                                if(carElement.getDirection() == 1){// to left
                                    if(roadWay.get().getTranslateX() - carElement.getPosition() < 60 && roadWay.get().getTranslateX() - carElement.getPosition() > -60){
                                        carElement.setNearWay(true);
                                    }else{
                                        carElement.setNearWay(false);
                                    }
                                }else{
                                    if(carElement.getPosition() - roadWay.get().getTranslateX() < 60 && carElement.getPosition() - roadWay.get().getTranslateX() > -60){
                                        carElement.setNearWay(true);
                                    }else{
                                        carElement.setNearWay(false);
                                    }
                                }
                            }

                        // checking for person crash

                        for(Person person:personElements){

                            if((roads*2 - person.getPersonYPosition())+1 != carElement.getRoadNumber()){
                                continue;
                            }

                            // check for road bridge

                            System.out.println(person.get().getTranslateX());
                            if(carElement.getDirection() == 1){// to left
                                if(person.get().getTranslateX() - carElement.getPosition() < 10 && person.get().getTranslateX() - carElement.getPosition() > -10){
                                    // check whether person is on bridge or not
                                    if(!onBridge(person)){
                                        stopAll();
                                        showFinisher();
                                    }
                                }
                            }else{
                                if(carElement.getPosition() - person.get().getTranslateX() < 10 && carElement.getPosition() - person.get().getTranslateX() > -10){
                                    if(!onBridge(person)){
                                        stopAll();
                                        showFinisher();
                                    }
                                }
                            }

                        }



                    }

                });
            }
        }, 100, 100);

    }

    private void removeCar(Car car){
        for(Road roadElement:roadsElements){
            if(roadElement.getRowIndex() < roads+1){
                if(roadElement.getRowIndex() == car.getRoadNumber()){
                    roadElement.get().getChildren().remove(car.get());
                }
            }else{
                if(roadElement.getRowIndex() - 1 == car.getRoadNumber()){
                    roadElement.get().getChildren().remove(car.get());
                }
            }
        }
    }

    private void addCarToRow(Car car, int roadNumber, int tranlsateX){
        for(Road roadElement:roadsElements){
            if(roadElement.getRowIndex() < roads+1){
                if(roadElement.getRowIndex() == roadNumber){
                    roadElement.get().getChildren().add(car.get());
                    car.get().setTranslateX(tranlsateX);
                }
            }else{
                if(roadElement.getRowIndex() - 1 == roadNumber){
                    roadElement.get().getChildren().add(car.get());
                    car.get().setTranslateX(tranlsateX);
                }
            }
        }
    }

    private void stopAll(){

        this.gameControllerTimer.cancel();
        this.carGeneratorTimer.cancel();
        this.timePassTimer.cancel();

        for(Car carElement:carElements){
            carElement.stop();
        }
    }

    private void showFinisher(){

        LostModal lostModal = new LostModal();
        lostModal.show();

        Stage stage = (Stage) wrapper.getScene().getWindow();
        stage.close();

    }

    private FinishModalController showWin(){
        FinishModal finishModal = new FinishModal();
        return finishModal.show();
    }

    private boolean onBridge(Person person){

        for(RoadBridge roadBridge:roadBridges){

            if(roadBridge.getPosition() - person.get().getTranslateX() <= 45 && person.getPersonYPosition() != 0){
                return true;
            }

        }

        return false;

    }

}
