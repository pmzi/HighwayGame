package sample.Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.Controllers.BaseController;
import sample.Controllers.GameController;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by pmzi on 7/8/2018.
 */
public class RankingModel implements Writable, FullReadable, Readable {

    public static void save(BaseController input){

        String name = ((GameController) input).getSavename();
        int timePassed = ((GameController) input).getTimePassed();
        int score = ((GameController) input).getScore();

        JSONArray rankings = read();
        JSONObject newRank = new JSONObject();
        newRank.put("name", name);
        newRank.put("score", score);
        newRank.put("timePassed", timePassed);
        rankings.add(newRank);

        Path path = Paths.get("src/sample/DB/ranking.json", new String[0]);
        String newPath = path.toAbsolutePath().toString();

        try(FileWriter file = new FileWriter(newPath);) {

            file.write(rankings.toJSONString());
            file.flush();

        } catch (IOException e) {

        }


    }

    public static JSONArray read(){
        Path path = Paths.get("src/sample/DB/ranking.json", new String[0]);
        String newPath = path.toAbsolutePath().toString();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(newPath));

            JSONArray jsonArray = (JSONArray) obj;

            return jsonArray;


        }catch (Exception e){
            return null;
        }
    }

    private String name;
    private int score;
    private int timePassed;

    public RankingModel(String name, int score, int timePassed){
        this.name = name;
        this.score = score;
        this.timePassed = timePassed;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }

    public int getTimePassed(){
        return this.timePassed;
    }

}
