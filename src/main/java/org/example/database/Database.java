package org.example.database;

import org.example.services.JSON;
import java.sql.*;

public class Database{
    Connection dbConnection = null;
    JSON json = new JSON();

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + json.reader().getDbHost() + ":"
                + json.reader().getDbPort() + "/" + json.reader().dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        this.dbConnection = DriverManager.getConnection(connectionString, json.reader().getDbUser(), json.reader().getDbPass());

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
}