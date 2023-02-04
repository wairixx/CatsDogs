package org.example.entities;

import lombok.Getter;

public class DatabaseConnFromFile {
    @Getter
    public String dbHost;
    @Getter
    public String dbPort;
    @Getter
    public String dbUser;
    @Getter
    public String dbPass;
    @Getter
    public String dbName;

    public DatabaseConnFromFile(String dbHost, String dbPort, String dbUser, String dbPass, String dbName) {
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        this.dbName = dbName;
    }
}
