package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.Helpers.WindowHelper;
import sample.Models.ReplyModel;
import sample.PageBuilder.GamePage;

/**
 * Created by pmzi on 7/7/2018.
 */
public class ReplyController extends BaseController {

    private ObservableList<ReplyModel> data =
            FXCollections.observableArrayList(

            );
    @FXML
    private GridPane wrapper;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<ReplyModel, String> nameCol;
    @FXML
    private TableColumn<ReplyModel, Integer> scoreCol;
    @FXML
    private TableColumn<ReplyModel, Integer> timePassedCol;

    public void insertData(){

        this.readData();

        tableView.setRowFactory(tv -> {
            TableRow<ReplyModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ReplyModel rowData = row.getItem();
                    load(rowData.getName());
                }
            });
            return row ;
        });

        nameCol.setCellValueFactory(new PropertyValueFactory<ReplyModel,String>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<ReplyModel,Integer>("score"));
        timePassedCol.setCellValueFactory(new PropertyValueFactory<ReplyModel,Integer>("timePassed"));
        tableView.setItems(data);
    }

    private void readData(){

        JSONArray savedData = ReplyModel.read();
        int length = savedData.size();
        for(int i =0;i<length;i++){
            JSONObject tempObj = (JSONObject) savedData.get(i);
            ReplyModel tempData = new ReplyModel(tempObj.get("name").toString(), Integer.parseInt(tempObj.get("score").toString()),Integer.parseInt(tempObj.get("timePassed").toString()));
            data.add(tempData);
        }

    }

    private void load(String name){

        JSONObject data = ReplyModel.getSpecific(name);

        GamePage gamePage = new GamePage();

        this.exit();

        gamePage.loadReply(data);

    }

    public void exit(){
        WindowHelper.hideCurrent((Stage) wrapper.getScene().getWindow());
    }

}
