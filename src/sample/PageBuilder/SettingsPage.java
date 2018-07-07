package sample.PageBuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsPage {

    public void show(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/settings.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 1600, 520);
            Stage stage = new Stage();
            stage.setTitle("Traffic Simulator Map Builder");
            scene.getStylesheets().add("sample/assets/css/main.css");
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
