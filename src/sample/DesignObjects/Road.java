package sample.DesignObjects;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.Controllers.RoadSettingsController;
import sample.PageBuilder.RoadSettingsModal;

/**
 * Created by pmzi on 7/7/2018.
 */
public class Road extends DesignObject{

    private GridPane road = new GridPane();
    private int randomize=1;
    private int speedStart=80;
    private int speedEnd=140;

    public Road(int rowPosition, boolean shouldHaveEvents){

        this.create(rowPosition);
        if(shouldHaveEvents){
            this.initEvents();
        }

    }

    public void setRandomize(int randomize){
        this.randomize = randomize;
    }

    public void setSpeedStart(int speedStart){
        this.speedStart = speedStart;
    }

    public void setSpeedEnd(int speedEnd){
        this.speedEnd = speedEnd;
    }

    public int getRandomize(){
        return this.randomize;
    }

    public int getSpeedStart(){
        return this.speedStart;
    }

    public int getSpeedEnd(){
        return this.speedEnd;
    }

    private void create(int rowPosition){

        road.getStyleClass().add("road");

        GridPane.setRowIndex(road, rowPosition);

        GridPane.setColumnIndex(road, 0);
    }

    public void setRowIndex(int rowIndex){
        GridPane.setRowIndex(this.road, rowIndex);
    }
    public int getRowIndex(){
        return GridPane.getRowIndex(this.road);
    }

    private void initEvents(){
        road.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg) {

                RoadSettingsController controller = RoadSettingsModal.show();
                if(!controller.randomize.getText().equals(null) && !controller.randomize.getText().equals("")){
                    System.out.println(controller.randomize.getText().trim());
                    randomize = Integer.parseInt(controller.randomize.getText());
                }

                speedStart = (int)controller.speedStart.getValue();

                speedEnd = (int)controller.speedEnd.getValue();

            }

        });
    }

    public GridPane get(){
        return this.road;
    }

}
