package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.PageBuilder.RoadSettingsModal;

/**
 * Created by pmzi on 7/6/2018.
 */
public class SettingsController {

    @FXML
    private Button toolboxPerson;
    @FXML
    private GridPane roadWrapper;

    public void onDeleteIconClicked(){

    }

    public void showRoadSettings(){

        RoadSettingsModal roadSettingsModal = new RoadSettingsModal();
        roadSettingsModal.show();

    }



}
