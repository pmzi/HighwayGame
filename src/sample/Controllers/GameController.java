package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.PageBuilder.SaveModal;


public class GameController {

    @FXML
    private Button exitIcon;

    @FXML
    public void showSave(){

        SaveModal saveModal = new SaveModal();
        saveModal.show();

    }

}
