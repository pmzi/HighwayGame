package sample.Helpers;

import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 * Created by pmzi on 7/7/2018.
 */
public class WindowHelper {

    public static void hideCurrent(ActionEvent e){
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

}
