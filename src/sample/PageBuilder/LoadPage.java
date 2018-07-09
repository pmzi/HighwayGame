package sample.PageBuilder;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Controllers.LoadController;
import sample.Helpers.WindowHelper;

import java.io.IOException;

/**
 * Created by pmzi on 7/7/2018.
 */
public class LoadPage {

    public void show(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Views/load.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            LoadController controller = fxmlLoader.getController();
            controller.insertData();
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(root, 600, 500);
            Stage stage = new Stage();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent windowEvent) {
                    WindowHelper.hideCurrent(stage);
                }
            });

            stage.setTitle("Load a game");
            scene.getStylesheets().add("sample/assets/css/main.css");
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
