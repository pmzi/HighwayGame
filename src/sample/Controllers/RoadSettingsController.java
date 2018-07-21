package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Helpers.WindowHelper;


/**
 * Created by pmzi on 7/7/2018.
 */
public class RoadSettingsController extends BaseController {

    @FXML
    public TextField randomize;
    @FXML
    public Slider speedStart;
    @FXML
    public Slider speedEnd;

    public void save(){
        this.exit();
    }

    public void exit(){
        WindowHelper.hideCurrent((Stage) randomize.getScene().getWindow());
    }

}
