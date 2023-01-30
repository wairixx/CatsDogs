package org.example.database;

import org.example.entities.Configs;

import java.sql.*;

public class Database extends Configs {
    Connection dbConnection = null;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        this.dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return this.dbConnection;
    }

    public Database() {
    }

    public ResultSet getResultSet(String SQL) {
        ResultSet resultSet = null;
        try {
            Statement statement = (getDbConnection().createStatement());

            resultSet = statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet getResultSet(String SQL, String... params) {
        ResultSet rs = null;
        try {
            PreparedStatement statement = (getDbConnection()).prepareStatement(SQL);
            for (int i = 1; i <= params.length; i++) {
                statement.setString(i, params[i - 1]);
            }
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean modifyDB(String SQL, Integer date_id, Integer user_id, String... params) {
        boolean isModify = false;
        try {
            PreparedStatement statement = (getDbConnection()).prepareStatement(SQL);
            for (int i = 1; i <= params.length; i++) {
                statement.setString(i, params[i - 1]);
            }
            isModify = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return isModify;
    }

    public boolean modifyDB(String SQL, String user_id, String service_date, int userChoice) {
        boolean isModify = false;
        try {
            PreparedStatement statement = (getDbConnection()).prepareStatement(SQL);
            isModify = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return isModify;
    }

    public boolean modifyD1(String SQL, Integer... params) {
        boolean isModify = false;
        try {
            PreparedStatement statement = (dbConnection.prepareStatement(SQL));
            for (int i = 1; i <= params.length; i++) {
                statement.setInt(i, params[i - 1]);
            }
            isModify = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return isModify;
    }
}