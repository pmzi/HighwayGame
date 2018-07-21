package sample.PageBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Controllers.Controller;

import java.io.IOException;

public class MainPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../Views/main.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Controller controller = fxmlLoader.getController();
        controller.beforeMount();

        // load the tron font.
//        Font.loadFont(
//                Main.class.getResource("@assets/fonts/Avenir.otf").toExternalForm(),
//                10
//        );

        primaryStage.setTitle("Traffic Simulator");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 600, 520);
        scene.getStylesheets().add("sample/assets/css/main.css");
        primaryStage.getIcons().add(new Image("sample/assets/images/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void launchIt() {
        launch();
    }

    public void show(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../Views/main.fxml"));
        try{
            Parent root = (Parent) fxmlLoader.load();
            Controller controller = fxmlLoader.getController();
            controller.beforeMount();
            Scene scene = new Scene(root, 600, 520);
            Stage stage = new Stage();
            stage.setTitle("Traffic Simulator");
            scene.getStylesheets().add("sample/assets/css/main.css");
            stage.setScene(scene);
            stage.getIcons().add(new Image("sample/assets/images/icon.png"));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
