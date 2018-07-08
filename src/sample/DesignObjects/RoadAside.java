package sample.DesignObjects;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * Created by pmzi on 7/7/2018.
 */
public class RoadAside extends DesignObject {

    private HBox roadAside = new HBox();
    private ArrayList<Person> persons = new ArrayList<>();

    public RoadAside(int rowPosition, int persons){

        this.create(rowPosition);
        this.createInsides(persons);

    }

    private void create(int rowPosition){

        roadAside.setAlignment(Pos.CENTER_RIGHT);

        roadAside.setSpacing(10);

        roadAside.getStyleClass().add("rowAside");

        roadAside.setAlignment(Pos.CENTER);

        GridPane.setRowIndex(roadAside, rowPosition);

        GridPane.setColumnIndex(roadAside, 0);

    }

    /*
        <Label styleClass="person">

        </Label>
     */

    private void createInsides(int persons){
        for(int i=0;i<persons;i++){
            Person person = new Person();
            this.persons.add(person);
            roadAside.getChildren().add(person.get());
        }
    }

    public ArrayList<Person> getPersons(){
        return this.persons;
    }

    public HBox get(){
        return this.roadAside;
    }

}
