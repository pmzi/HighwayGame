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

    public Label get(){
        return this.person;
    }

}
