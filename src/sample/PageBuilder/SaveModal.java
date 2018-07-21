package sample.PageBuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by pmzi on 7/6/2018.
 */
public class SaveModal extends BaseModalBuilder {

    public <T> T show(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/saveModal.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            T controller = fxmlLoader.getController(); // Retrieve the controller
            Scene scene = new Scene(root, 300, 220);
            Stage stage = new Stage();
            stage.setTitle("Save Modal");
            scene.getStylesheets().add("sample/assets/css/modal.css");
            stage.setScene(scene);
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            return controller;

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
