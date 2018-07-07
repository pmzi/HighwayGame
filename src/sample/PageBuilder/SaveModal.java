package sample.PageBuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by pmzi on 7/6/2018.
 */
public class SaveModal {

    public void show(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/saveModal.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 300, 220);
            Stage stage = new Stage();
            stage.setTitle("Save Modal");
            scene.getStylesheets().add("sample/assets/css/modal.css");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
