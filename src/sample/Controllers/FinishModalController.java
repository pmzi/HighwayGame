package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Helpers.WindowHelper;

/**
 * Created by pmzi on 7/7/2018.
 */
public class FinishModalController extends BaseController{

    @FXML
    public TextField saveText;

    @FXML
    public void save(){
        this.exit();
    }

    public void exit(){
        WindowHelper.hideCurrent((Stage) saveText.getScene().getWindow());
    }

}
