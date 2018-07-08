package sample.DesignObjects;

import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Created by pmzi on 7/7/2018.
 */
public class RoadWay extends DesignObject {

    private VBox roadWay = new VBox();

    private boolean shouldBeDeleted = false;

    private boolean isSelected = false;

    public RoadWay(int highwayHeight){

        this.create(highwayHeight);
        this.initEvents();

    }

    public void setMaxHeight(int highwayHeight){
        roadWay.setMaxHeight(highwayHeight);
    }

    public void changePosition(int x){
        roadWay.setTranslateX(roadWay.getTranslateX() + x);
    }

    private void initEvents(){
        roadWay.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg) {

                isSelected = !isSelected;

            }

        });
    }

    public boolean isSelected(){

        return this.isSelected;

    }

    public void unSelect(){
        this.isSelected = false;
    }

    private void create(int highwayHeight){
        roadWay.getStyleClass().add("roadWay");
        roadWay.setMaxHeight(highwayHeight);
        GridPane.setColumnIndex(roadWay, 1);
        GridPane.setValignment(roadWay, VPos.CENTER);
    }

    public void setColumnIndex(int columnIndex){
        GridPane.setColumnIndex(roadWay, columnIndex);
    }
    public void setRowIndex(int rowIndex){
        GridPane.setRowIndex(roadWay, rowIndex);
    }
    public void setStyleClass(String styleClass){
        this.roadWay.getStyleClass().clear();
        this.roadWay.getStyleClass().add(styleClass);
    }
    public void setMaxWidth(int maxWidth){
        roadWay.setMaxWidth(maxWidth);
    }

    public VBox get(){
        return this.roadWay;
    }

}
