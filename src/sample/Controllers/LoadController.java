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
import sample.Models.SaveModel;
import sample.PageBuilder.GamePage;

/**
 * Created by pmzi on 7/7/2018.
 */
public class LoadController {

    private ObservableList<SaveModel> data =
            FXCollections.observableArrayList(

            );
    @FXML
    private GridPane wrapper;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<SaveModel, String> nameCol;
    @FXML
    private TableColumn<SaveModel, Integer> scoreCol;
    @FXML
    private TableColumn<SaveModel, Integer> timePassedCol;

    public void insertData(){

        this.readData();

        tableView.setRowFactory(tv -> {
            TableRow<SaveModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SaveModel rowData = row.getItem();
                    load(rowData.getName());
                }
            });
            return row ;
        });

        nameCol.setCellValueFactory(new PropertyValueFactory<SaveModel,String>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<SaveModel,Integer>("score"));
        timePassedCol.setCellValueFactory(new PropertyValueFactory<SaveModel,Integer>("timePassed"));
        tableView.setItems(data);
    }

    private void readData(){

        JSONArray savedData = SaveModel.readAll();

        for(int i =0;i<savedData.size();i++){
            JSONObject tempObj = (JSONObject) savedData.get(i);
            SaveModel tempData = new SaveModel(tempObj.get("name").toString(), Integer.parseInt(tempObj.get("score").toString()),Integer.parseInt(tempObj.get("timePassed").toString()));
            data.add(tempData);
        }

    }

    private void load(String name){

        JSONObject data = SaveModel.getSpecific(name);

        GamePage gamePage = new GamePage();

        Stage stage = (Stage) wrapper.getScene().getWindow();
        stage.close();

        gamePage.loadPage(data);

    }

}
