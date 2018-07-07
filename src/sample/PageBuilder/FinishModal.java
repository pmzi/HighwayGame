package sample.PageBuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by pmzi on 7/7/2018.
 */
public class FinishModal {

    public <T> T show(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../Views/finishModal.fxml"));
        T controller = fxmlLoader.getController(); // Retrieve the controller
        try{

            Scene scene = new Scene(fxmlLoader.load(), 800, 327);
            Stage stage = new Stage();
            stage.setTitle("Save Your Score");
            scene.getStylesheets().add("sample/assets/css/modal.css");
            stage.setScene(scene);
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            return controller;
        }catch (IOException e){
            e.printStackTrace();
        }

        return controller;

    }

}
