package org.example.database;

import org.example.entities.Configs;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PurchaseDAO {
    private final Database databaseConnection;

    public PurchaseDAO(Database databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    public void buyProduct(int user_id, int userChoice, int product_quantity) {
        try {
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_BUY_PRODUCT);
            prSt.setInt(1, userChoice);
            prSt.setInt(2, user_id);
            prSt.setInt(3, product_quantity);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void makeDate(Integer month, Integer day, String time, int user_id) {
        try {
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_MAKE_DATE);
            prSt.setInt(1, user_id);
            prSt.setInt(2, month);
            prSt.setInt(3, day);
            prSt.setString(4, time);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void buyService(int user_id, int userChoice, int date_id) {
        try {
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_BUY_SERVICE);
            prSt.setInt(1, user_id);
            prSt.setInt(2, userChoice);
            prSt.setInt(3, date_id);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
