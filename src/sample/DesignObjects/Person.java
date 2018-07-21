package sample.DesignObjects;

import javafx.scene.control.Label;

/**
 * Created by pmzi on 7/7/2018.
 */
public class Person extends DesignObject implements HMovable,VMovable {

    private Label person = new Label();

    private boolean didPass = false;

    private int personYPosition = 0;

    public Person(){

        this.create();

    }

    public void setDidPass(boolean didPass){
        this.didPass = didPass;
    }

    public boolean getDidPass(){
        return didPass;
    }

    public int getPersonYPosition(){
        return personYPosition;
    }
    public void setPersonYPosition(int personYPosition){
        this.personYPosition += personYPosition;
    }

    public void goUp(){
        this.personYPosition += 1;
    }

    public void goLeft(){

    }

    public void goRight(){

    }

    public void goDown(){
        this.personYPosition += -1;
    }

    private void create(){
        person.getStyleClass().add("person");
    }

    public void changePosition(int x, int y){
        if(x == -1){
            this.person.setTranslateY(this.person.getTranslateY() + y);
        }else if(y == -1){
            this.person.setTranslateX(this.person.getTranslateX() + x);
        }

    }

    public Label get(){
        return this.person;
    }

}
