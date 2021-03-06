package sample.DesignObjects;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * Created by pmzi on 7/7/2018.
 */
public class RoadAside extends DesignObject {

    private GridPane roadAside = new GridPane();
    private ArrayList<Person> persons = new ArrayList<>();

    public RoadAside(int rowPosition, int persons){

        this.create(rowPosition);
        this.createInsides(persons);

    }

    private void create(int rowPosition){

        roadAside.getStyleClass().add("rowAside");

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
            person.changePosition(i*40,-1);
            this.persons.add(person);
            roadAside.getChildren().add(person.get());
        }
    }

    private void appendInsides(){

        for(int i=0;i<this.persons.size();i++){
            System.out.printf("%f %f\n",this.persons.get(i).get().getTranslateX(),this.persons.get(i).get().getTranslateY());
            roadAside.getChildren().add(this.persons.get(i).get());
        }

    }

    public ArrayList<Person> getPersons(){
        return this.persons;
    }

    public void setPersons(ArrayList<Person> persons){
        this.persons = persons;
        this.appendInsides();
    }

    public GridPane get(){
        return this.roadAside;
    }

}
