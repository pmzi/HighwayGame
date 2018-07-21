package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Helpers.WindowHelper;

/**
 * Created by pmzi on 7/6/2018.
 */
public class SaveModalController extends BaseController {

    @FXML
    public TextField saveTextField;

    @FXML
    public void saveAndClose(ActionEvent e){
        WindowHelper.hideCurrent(e);
    }

    public void closeWithoutSave(ActionEvent e){
        this.saveTextField.setText("");
        this.exit();
    }

    public void exit(){
        WindowHelper.hideCurrent((Stage) saveTextField.getScene().getWindow());
    }

}
