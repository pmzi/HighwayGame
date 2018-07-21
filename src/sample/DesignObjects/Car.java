package sample.DesignObjects;

import javafx.scene.control.Label;
import sample.Helpers.RandomNumberGenerator;

/**
 * Created by pmzi on 7/8/2018.
 */
public class Car extends DesignObject implements HMovable {

    private int primarySpeed = 0;
    private int currSpeed = 0;
    private int roadNumber = 0;

    private int initiationX = 0;

    private int creationTime;

    private boolean isHighSpeed = false;
    private boolean nearWay = false;

    int direction = 1;

    private Label car = new Label();

    public Car(int speedStart, int speedEnd, int roadNumber, int direction, int creationTime){
        this.create(speedStart,speedEnd,roadNumber,direction,creationTime);
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

    public void create(int speedStart, int speedEnd, int roadNumber, int direction,int creationTime) {
        this.creationTime = creationTime;
        this.direction = direction;

        if(direction == 1){
            this.car.getStyleClass().addAll("car","simpleLeftCar","rightCar");
        }else{
            this.car.getStyleClass().addAll("car","simpleRightCar","leftCar");
        }

        int randomNum = RandomNumberGenerator.randomNumberInRange(speedStart,speedEnd);
        this.primarySpeed = randomNum;
        this.currSpeed = randomNum;
        this.roadNumber = roadNumber;

        this.car.toFront();

    }

    public int getCreationTime(){
        return this.creationTime;
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

    public int getPrimarySpeed(){
        return  this.primarySpeed;
    }

    public void setInitiationX(int x){
        this.initiationX = x;
    }

    public void move(){

        if(this.nearWay){
            this.currSpeed /= 2;
        }else if(this.isHighSpeed){
            this.currSpeed *= 2;
        }

        if(direction == 1){
            this.goLeft();
        }else{
            this.goRight();
        }

        this.currSpeed = this.primarySpeed;

    }

    public void goRight(){
        int prevX = (int) this.car.getTranslateX();
        if(initiationX != 0){
            prevX = initiationX;
            initiationX = 0;
        }
        this.car.setTranslateX(prevX + (this.currSpeed/20));
    }

    public void goLeft(){
        int prevX = (int) this.car.getTranslateX();
        if(initiationX != 0){
            prevX = initiationX;
            initiationX = 0;
        }
        this.car.setTranslateX(prevX - (this.currSpeed/20));
    }

    public void setNearWay(boolean nearWay){
        this.nearWay = nearWay;
    }

    public void setHighSpeed(boolean isHighSpeed){
        this.isHighSpeed = isHighSpeed;
    }

    public boolean getHighSpeed(){
        return this.isHighSpeed;
    }


    public void goUp(){
        this.roadNumber--;
    }

    public Label get(){
        return this.car;
    }

    public void setCurrSpeed(int speed){
        this.currSpeed = speed;
    }

    public void setPrimarySpeed(int speed){
        this.primarySpeed = speed;
    }

    public int getCurrSpeed(){
        return this.currSpeed;
    }

    public void stop(){
        this.currSpeed = 0;
    }

}
