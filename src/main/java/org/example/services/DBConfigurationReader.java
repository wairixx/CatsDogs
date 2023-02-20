package org.example.services;

import lombok.Setter;
import org.example.entities.DatabaseConfiguration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class DBConfigurationReader {
    @Setter
    private String path = "src/main/resources/DBConfiguration.json";

    public DatabaseConfiguration readDatabaseConfigFromFile() {
        JSONParser jsonParser = new JSONParser();
        FileReader reader;
        DatabaseConfiguration databaseConfiguration;
        try {
            reader = new FileReader(path);
            Object object;
            //TODO relative path

            object = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) object;
            databaseConfiguration = new DatabaseConfiguration(
                    (String) jsonObject.get("dbHost"),
                    (String) jsonObject.get("dbPort"),
                    (String) jsonObject.get("dbUser"),
                    (String) jsonObject.get("dbPass"),
                    (String) jsonObject.get("dbName"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return databaseConfiguration;
    }
}
