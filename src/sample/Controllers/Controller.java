package sample.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import sample.Helpers.MusicPlayer;
import sample.Helpers.URLOpener;
import sample.Helpers.WindowHelper;
import sample.PageBuilder.LoadPage;
import sample.PageBuilder.ReplyPage;
import sample.PageBuilder.ScorePage;
import sample.PageBuilder.SettingsPage;

import java.util.Optional;

public class Controller {

    private boolean isMusicPaused = false;

    private MusicPlayer musicPlayer = new MusicPlayer("1.mp3");

    @FXML
    private Button musicIcon;

    @FXML
    public void showLoadPage(ActionEvent e){

        WindowHelper.hideCurrent(e);
        musicPlayer.stop();
        LoadPage loadPage = new LoadPage();
        loadPage.show();

    }

    @FXML
    public void showScoresPage(ActionEvent e){

        WindowHelper.hideCurrent(e);
        musicPlayer.stop();
        ScorePage scorePage = new ScorePage();
        scorePage.show();

    }

    @FXML
    public void showReplyPage(ActionEvent e){

        WindowHelper.hideCurrent(e);
        musicPlayer.stop();
        ReplyPage replyPage = new ReplyPage();
        replyPage.show();

    }

    @FXML
    public void showSettingsPage(ActionEvent e){
        WindowHelper.hideCurrent(e);
        musicPlayer.stop();
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
    public void changeMusicStatus(){
        musicIcon.getStyleClass().clear();

        if (this.isMusicPaused) {
            // Let's play
            this.musicPlayer.play();

            musicIcon.getStyleClass().add("play");

        } else {
            this.musicPlayer.pause();
            musicIcon.getStyleClass().add("paused");
        }
        musicIcon.getStyleClass().add("gameIconicButtons");
        this.isMusicPaused = !this.isMusicPaused;
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

    public void beforeMount(){
        musicPlayer.play();
    }

}
