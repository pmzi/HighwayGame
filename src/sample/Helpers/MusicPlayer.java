package sample.Helpers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * Created by pmzi on 7/8/2018.
 */
public class MusicPlayer {

    private Media music;
    private MediaPlayer mediaPlayer;

    public MusicPlayer(String musicName){
        music = new Media(Paths.get("src/sample/assets/musics/"+musicName).toUri().toString());
        mediaPlayer = new MediaPlayer(music);
    }

    public void play(){

        mediaPlayer.play();
    }

    public void pause(){
        mediaPlayer.pause();
    }

    public void stop(){
        mediaPlayer.stop();
    }

}
