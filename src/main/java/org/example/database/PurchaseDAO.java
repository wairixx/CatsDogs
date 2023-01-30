package org.example.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PurchaseDAO {
    private Database databaseConnection;

    public PurchaseDAO(Database databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private static final String SQL_BUY_PRODUCT = "INSERT INTO new_test.users_products " +
            "(user_id, product_id, product_quantity)" + " VALUES (?,?,?)";
    private static final String SQL_MAKE_DATE = "INSERT INTO new_test.date " +
            "(user_id, month, day, time)" + " VALUES (?,?,?,?)";
    private static final String SQL_BUY_SERVICE = "INSERT INTO new_test.users_services " +
            "(user_id, service_id, date_id)" + " VALUES (?,?,?)";

    public void buyProduct(int user_id, int userChoice, int product_quantity) {
        try {
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(SQL_BUY_PRODUCT);
            prSt.setInt(1, userChoice);
            prSt.setInt(2, user_id);
            prSt.setInt(3, product_quantity);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void makeDate(Integer month, Integer day, String time, int user_id, int userChoice) {
        try {
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(SQL_MAKE_DATE);
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
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(SQL_BUY_SERVICE);
            prSt.setInt(1, user_id);
            prSt.setInt(2, userChoice);
            prSt.setInt(3, date_id);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
