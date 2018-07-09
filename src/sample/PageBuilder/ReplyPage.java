package sample.PageBuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Controllers.ReplyController;

import java.io.IOException;

/**
 * Created by pmzi on 7/7/2018.
 */
public class ReplyPage {

    public void show(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/reply.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ReplyController controller = fxmlLoader.getController();
            controller.insertData();
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(root, 600, 500);
            Stage stage = new Stage();
            stage.setTitle("Load a game");
            scene.getStylesheets().add("sample/assets/css/main.css");
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
