package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import sample.PageBuilder.SaveModal;

import java.util.Optional;


public class GameController {

    @FXML
    private Button exitIcon;

    @FXML
    public void showSave(){

        SaveModal saveModal = new SaveModal();
        saveModal.show();

    }

    @FXML
    public void exit(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            Platform.exit();
        }

    }

}
