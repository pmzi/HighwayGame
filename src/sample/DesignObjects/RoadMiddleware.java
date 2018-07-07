package sample.DesignObjects;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Created by pmzi on 7/7/2018.
 */
public class RoadMiddleware extends DesignObject {

    private HBox roadMiddleware = new HBox();

    public RoadMiddleware(int rowPosition){

        this.create(rowPosition);

    }

    private void create(int rowPosition){
        roadMiddleware.setAlignment(Pos.CENTER_RIGHT);

        roadMiddleware.setSpacing(10);

        roadMiddleware.getStyleClass().add("roadMiddleware");

        GridPane.setRowIndex(roadMiddleware, rowPosition);

        GridPane.setColumnIndex(roadMiddleware, 0);
    }

    public HBox get(){
        return this.roadMiddleware;
    }

}
