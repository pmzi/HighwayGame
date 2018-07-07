//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sample.Models;

import org.json.simple.JSONObject;
import sample.Controllers.SettingsController;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SettingsModel {

    public static void save(SettingsController input) {
        JSONObject primaryObj = new JSONObject();
        primaryObj.put("persons", Integer.valueOf(input.getPersonsLength()));
        Path path = Paths.get("src/sample/DB/settings.json", new String[0]);
        String newPath = path.toAbsolutePath().toString();

        try(FileWriter file = new FileWriter(newPath);) {

                file.write(primaryObj.toJSONString());
                file.flush();
        } catch (IOException e) {

        }

    }
}
