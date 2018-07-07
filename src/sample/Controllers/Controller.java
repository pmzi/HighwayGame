package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Helpers.WindowHelper;
import sample.PageBuilder.LoadPage;

public class Controller {

    @FXML
    public void showLoadPage(ActionEvent e){

        WindowHelper.hideCurrent(e);

        LoadPage loadPage = new LoadPage();
        loadPage.show();

    }

}
