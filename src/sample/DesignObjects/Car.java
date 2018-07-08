package sample.DesignObjects;

import javafx.scene.control.Label;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by pmzi on 7/8/2018.
 */
public class Car extends DesignObject {

    private int primarySpeed = 0;
    private int currSpeed = 0;
    private int roadNumber = 0;

    int direction = 1;

    private Label car = new Label();

    public Car(int speedStart, int speedEnd, int roadNumber, int direction){
        this.create(speedStart,speedEnd,roadNumber,direction);
    }

    /*
    <Label styleClass="car">-->
                <!--<graphic>-->
                    <!--<ImageView>-->
                        <!--<Image url="@../assets/images/cars/right/car.png" />-->
                    <!--</ImageView>-->
                <!--</graphic>-->
            <!--</Label>
     */

    public void create(int speedStart, int speedEnd, int roadNumber, int direction) {

        this.direction = direction;

        if(direction == 1){
            this.car.getStyleClass().addAll("car","simpleCar","rightCar");
        }else{
            this.car.getStyleClass().addAll("car","simpleCar","leftCar");
        }

        int randomNum = ThreadLocalRandom.current().nextInt(speedStart, speedEnd + 1);

        this.primarySpeed = randomNum;
        this.currSpeed = randomNum;
        this.roadNumber = roadNumber;

    }

    public int getRoadNumber(){
        return this.roadNumber;
    }

    public int getPosition(){
        return (int) this.car.getTranslateX();
    }

    public int getDirection(){
        return this.direction;
    }

    public void move(){
        if(direction == 1){
            this.car.setTranslateX(this.car.getTranslateX() - (this.currSpeed/10));
        }else{
            this.car.setTranslateX(this.car.getTranslateX() + (this.currSpeed/10));
        }

    }

    public void stop(){

    }

    public void lessenSpeed(){}

    public void goUp(int roadHeight){
        this.car.setTranslateY(this.car.getTranslateY() - roadHeight);
        this.roadNumber--;
    }

    public Label get(){
        return this.car;
    }

    public void setCurrSpeed(int speed){
        this.currSpeed = speed;
    }

    public int getCurrSpeed(){
        return this.currSpeed;
    }

}
