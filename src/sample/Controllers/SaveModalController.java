package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Helpers.WindowHelper;

/**
 * Created by pmzi on 7/6/2018.
 */
public class SaveModalController {

    @FXML
    public TextField saveTextField;

    @FXML
    public void saveAndClose(ActionEvent e){
        WindowHelper.hideCurrent(e);
    }

    public void closeWithoutSave(ActionEvent e){
        this.saveTextField.setText("");
        WindowHelper.hideCurrent(e);
    }

}
