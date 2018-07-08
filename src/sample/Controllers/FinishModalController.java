package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Helpers.WindowHelper;

/**
 * Created by pmzi on 7/7/2018.
 */
public class FinishModalController {

    @FXML
    public TextField saveText;

    @FXML
    public void save(ActionEvent e){
        WindowHelper.hideCurrent(e);
    }

}
