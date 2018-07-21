package sample.DesignObjects;

import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Created by pmzi on 7/7/2018.
 */
public class RoadBridge extends DesignObject {

    private VBox roadBridge = new VBox();

    private boolean shouldBeDeleted = false;

    private boolean isSelected = false;

    public RoadBridge(int highwayHeight){

        this.create(highwayHeight);
        this.initEvents();

    }

    public void setMaxHeight(int highwayHeight){
        roadBridge.setMaxHeight(highwayHeight);
    }

    public void changePosition(int x){
        roadBridge.setTranslateX(roadBridge.getTranslateX() + x);
    }
    public int getPosition(){
        return (int) roadBridge.getTranslateX();
    }


    private void create(int highwayHeight){
        roadBridge.getStyleClass().add("roadBridge");
        roadBridge.setMaxHeight(highwayHeight);
        GridPane.setColumnIndex(roadBridge, 1);
        GridPane.setValignment(roadBridge, VPos.CENTER);
    }

    private void initEvents(){
        roadBridge.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg) {
                isSelected = !isSelected;
            }

        });
    }

    public boolean isSelected(){

        return this.isSelected;

    }

    public void setRowIndex(int rowIndex){
        GridPane.setRowIndex(roadBridge, rowIndex);
    }
    public void setColumnIndex(int columnIndex){
        GridPane.setColumnIndex(roadBridge, columnIndex);
    }
    public void setStyleClass(String styleClass){
        this.roadBridge.getStyleClass().clear();
        this.roadBridge.getStyleClass().add(styleClass);
    }
    public void setMaxWidth(int maxWidth){
        roadBridge.setMaxWidth(maxWidth);
    }

    public void unSelect(){
        this.isSelected = false;
    }

    public VBox get(){
        return this.roadBridge;
    }

}
