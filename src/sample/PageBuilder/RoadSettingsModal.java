package sample.PageBuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by pmzi on 7/7/2018.
 */
public class RoadSettingsModal {

    public static Object show() {
        RoadSettingsModal roadSettingsModal = new RoadSettingsModal();
        return roadSettingsModal.showPage();
    }

    private Object showPage() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("../Views/roadSettingsModal.fxml"));
        Object controller = fxmlLoader.getController();

        try{
            Scene scene = new Scene((Parent)fxmlLoader.load(), 400.0D, 300.0D);
            Stage stage = new Stage();
            stage.setTitle("Road Settings");
            scene.getStylesheets().add("sample/assets/css/modal.css");
            stage.setScene(scene);
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            return controller;
        }catch (Exception e){
            return controller;
        }
    }

}
