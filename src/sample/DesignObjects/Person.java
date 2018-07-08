package sample.DesignObjects;

import javafx.scene.control.Label;

/**
 * Created by pmzi on 7/7/2018.
 */
public class Person extends DesignObject {

    private Label person = new Label();

    public Person(){

        this.create();

    }

    private void create(){
        person.getStyleClass().add("person");
    }

    public void changePosition(int x, int y){
        if(x == -1){
            this.person.setTranslateY(y);
        }else if(y == -1){
            this.person.setTranslateX(x);
        }

    }

    public Label get(){
        return this.person;
    }

}
