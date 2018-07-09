package sample.Helpers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import sample.PageBuilder.MainPage;

/**
 * Created by pmzi on 7/7/2018.
 */
public class WindowHelper {

    public static void hideCurrent(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    public static void hideCurrent(ActionEvent e, boolean showMain){
        ((Node)(e.getSource())).getScene().getWindow().hide();
        if(showMain){
            showHome();
        }
    }

    public static void hideCurrent(Shape item){
        Stage stage = (Stage) item.getScene().getWindow();
        stage.close();
        showHome();
    }

    public static void hideCurrent(Stage stage){
        stage.close();
        showHome();
    }

    public static void hideCurrent(Pane item){
        Stage stage = (Stage) item.getScene().getWindow();
        stage.close();
        showHome();
    }

    public static void hideCurrent(Pane item, boolean showMain){
        Stage stage = (Stage) item.getScene().getWindow();
        stage.close();
        if(showMain){
            showHome();
        }
    }

    private static void showHome(){
        MainPage mainPage = new MainPage();
        mainPage.show();
    }

}
