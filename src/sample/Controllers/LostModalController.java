package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Helpers.WindowHelper;
import sample.PageBuilder.MainPage;


/**
 * Created by pmzi on 7/8/2018.
 */
public class LostModalController {

    @FXML
    public void showMainMenu(ActionEvent e){

        WindowHelper.hideCurrent(e);

        MainPage mainPage = new MainPage();
        mainPage.show();

    }

}
