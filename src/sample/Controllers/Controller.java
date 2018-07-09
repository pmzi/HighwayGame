package sample.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.Helpers.URLOpener;
import sample.Helpers.WindowHelper;
import sample.PageBuilder.LoadPage;
import sample.PageBuilder.ReplyPage;
import sample.PageBuilder.ScorePage;
import sample.PageBuilder.SettingsPage;

import java.util.Optional;

public class Controller {

    @FXML
    public void showLoadPage(ActionEvent e){

        WindowHelper.hideCurrent(e);

        LoadPage loadPage = new LoadPage();
        loadPage.show();

    }

    @FXML
    public void showScoresPage(ActionEvent e){

        WindowHelper.hideCurrent(e);

        ScorePage scorePage = new ScorePage();
        scorePage.show();

    }

    @FXML
    public void showReplyPage(ActionEvent e){

        WindowHelper.hideCurrent(e);

        ReplyPage replyPage = new ReplyPage();
        replyPage.show();

    }

    @FXML
    public void showSettingsPage(ActionEvent e){
        WindowHelper.hideCurrent(e);

        SettingsPage settingsPage = new SettingsPage();
        settingsPage.show();
    }

    @FXML
    public void openGithub(){
        URLOpener.openWebpage("https://github.com/pmzi/JavaUniProject");
    }

    @FXML
    public void openDoc(){
        URLOpener.openWebpage("https://nerdpitch.io/show/javaUniProject");
    }

    @FXML
    public void exit() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }

    }

}
