package sample.DesignObjects;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sample.PageBuilder.RoadSettingsModal;

/**
 * Created by pmzi on 7/7/2018.
 */
public class Road extends DesignObject{

    private HBox road = new HBox();
    private int randomize=1;
    private int speedStart=10;
    private int speedEnd=20;

    public Road(int rowPosition){

        this.create(rowPosition);
        this.initEvents();

    }

    private void create(int rowPosition){

        road.setAlignment(Pos.CENTER_RIGHT);

        road.setSpacing(10);

        road.getStyleClass().add("road");

        GridPane.setRowIndex(road, rowPosition);

        GridPane.setColumnIndex(road, 0);
    }

    private void initEvents(){
        road.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg) {

                RoadSettingsModal.show();

            }

        });
    }

    public HBox get(){
        return this.road;
    }

}
