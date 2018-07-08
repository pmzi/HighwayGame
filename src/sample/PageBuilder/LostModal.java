package sample.PageBuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by pmzi on 7/8/2018.
 */
public class LostModal {

    public void show(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/lostModal.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 220);
            Stage stage = new Stage();
            stage.setTitle("Lost");
            scene.getStylesheets().add("sample/assets/css/modal.css");
            stage.setScene(scene);
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
