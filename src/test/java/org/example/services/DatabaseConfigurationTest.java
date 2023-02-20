package org.example.services;

import org.example.entities.DatabaseConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseConfigurationTest {
    private DBConfigurationReader dbConfigurationReader;
    private final String PATH_TO_TEST_CONFIG_FILE = "C:\\Users\\User\\IdeaProjects\\CatsDogs\\src\\test\\java\\resources\\DBConfigurationTest.json";
    private final String TEST_PORT = "8080";
    private final String TEST_HOST = "dbHostTest";
    private final String TEST_USER = "dbUserTest";
    private final String TEST_PASS ="dbPassTest";
    private final String TEST_NAME = "dbNameTest";

    @BeforeEach
    public void init(){
        dbConfigurationReader = new DBConfigurationReader();
        dbConfigurationReader.setPath(PATH_TO_TEST_CONFIG_FILE);
    }
    @Test
    public void readDatabaseConfigFromFileCorrectTest(){
        DatabaseConfiguration databaseConfiguration = dbConfigurationReader.readDatabaseConfigFromFile();

        Assertions.assertEquals(TEST_PORT, databaseConfiguration.getDbPort());
        Assertions.assertEquals(TEST_HOST, databaseConfiguration.getDbHost());
        Assertions.assertEquals(TEST_USER, databaseConfiguration.getDbUser());
        Assertions.assertEquals(TEST_PASS, databaseConfiguration.getDbPass());
        Assertions.assertEquals(TEST_NAME, databaseConfiguration.getDbName());
        //TODO make all Strings like 8080 constants and assert all other fields
    }
}
