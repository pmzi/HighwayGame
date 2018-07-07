package sample.PageBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by pmzi on 7/6/2018.
 */
public class GamePage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Views/game.fxml"));

        // load the tron font.
//        Font.loadFont(
//                Main.class.getResource("@assets/fonts/Avenir.otf").toExternalForm(),
//                10
//        );

        primaryStage.setTitle("Traffic Simulator");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 1600, 520);
        scene.getStylesheets().add("sample/assets/css/main.css");
        primaryStage.getIcons().add(new Image("sample/assets/images/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void launchIt() {
        launch();
    }
}
