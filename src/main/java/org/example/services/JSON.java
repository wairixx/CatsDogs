package org.example.services;
import org.example.entities.DatabaseConnFromFile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class JSON {
    public DatabaseConnFromFile reader() {
        JSONParser jsonParser = new JSONParser();
        FileReader reader;
        DatabaseConnFromFile databaseConnFromFile;
        try {
            reader = new FileReader("C:\\Users\\User\\IdeaProjects\\CatsDogs\\src\\main\\resources\\test.json");
        Object object;

            object = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject)object;
            databaseConnFromFile = new DatabaseConnFromFile((String) jsonObject.get("dbHost"),
                    (String) jsonObject.get("dbPort"),
                    (String) jsonObject.get("dbUser"),
                    (String) jsonObject.get("dbPass"),
                    (String) jsonObject.get("dbName"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }


        return databaseConnFromFile;
    }
}
