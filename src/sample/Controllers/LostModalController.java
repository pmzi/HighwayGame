package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Helpers.WindowHelper;
import sample.PageBuilder.MainPage;


/**
 * Created by pmzi on 7/8/2018.
 */
public class LostModalController extends BaseController {

    @FXML
    private GridPane wrapper;

    @FXML
    public void showMainMenu(ActionEvent e){

        this.exit();

        MainPage mainPage = new MainPage();
        mainPage.show();

    }

    public void exit(){
        WindowHelper.hideCurrent((Stage) wrapper.getScene().getWindow());
    }

}
