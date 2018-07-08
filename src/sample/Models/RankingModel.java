package sample.Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by pmzi on 7/8/2018.
 */
public class RankingModel {

    public static void save(String name, int score, int timePassed){

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

    private static JSONArray read(){
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

}
