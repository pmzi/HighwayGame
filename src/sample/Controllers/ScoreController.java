package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.Helpers.WindowHelper;
import sample.Models.RankingModel;

/**
 * Created by pmzi on 7/7/2018.
 */
public class ScoreController extends BaseController {

    private ObservableList<RankingModel> data =
            FXCollections.observableArrayList(

            );
    @FXML
    private GridPane wrapper;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<RankingModel, String> nameCol;
    @FXML
    private TableColumn<RankingModel, Integer> scoreCol;
    @FXML
    private TableColumn<RankingModel, Integer> timePassedCol;

    public void insertData(){

        this.readData();

        nameCol.setCellValueFactory(new PropertyValueFactory<RankingModel,String>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<RankingModel,Integer>("score"));
        timePassedCol.setCellValueFactory(new PropertyValueFactory<RankingModel,Integer>("timePassed"));
        tableView.setItems(data);
    }

    private void readData(){

        JSONArray savedData = RankingModel.read();

        for(int i =0;i<savedData.size();i++){
            JSONObject tempObj = (JSONObject) savedData.get(i);
            RankingModel tempData = new RankingModel(tempObj.get("name").toString(), Integer.parseInt(tempObj.get("score").toString()),Integer.parseInt(tempObj.get("timePassed").toString()));
            data.add(tempData);
        }

    }

    public void exit(){
        WindowHelper.hideCurrent((Stage) wrapper.getScene().getWindow());
    }
    
}
