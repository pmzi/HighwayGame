package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import sample.Helpers.WindowHelper;


/**
 * Created by pmzi on 7/7/2018.
 */
public class RoadSettingsController {

    @FXML
    public TextField randomize;
    @FXML
    public Slider speedStart;
    @FXML
    public Slider speedEnd;

    public void save(ActionEvent e){
        WindowHelper.hideCurrent(e);
    }

}
