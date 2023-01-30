package org.example.database;

import org.example.entities.Product;
import org.example.entities.Stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAO {
    private Database databaseConnection;

    public StockDAO(Database databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private static final String SQL_GET_ALL_STOCKS = "SELECT * FROM new_test.services;";
    private static final String SQL_CREATE_DATE = "INSERT INTO new_test.date" +
            "( date_id, month, day, time, user_id)" + " VALUES (?,?,?,?)";
    private static final String SQL_CHECK_MONTH = "SELECT * FROM new_test.date WHERE month = ?;";
    private static final String SQL_CHECK_DAY = "SELECT * FROM new_test.date WHERE day = ?;";
    private static final String SQL_CHECK_TIME = "SELECT * FROM new_test.date WHERE month = ? AND day = ? AND time = ?;";
    private static final String PRICE = "SELECT price FROM new_test.services WHERE service_id = ?;";
    private static final String DATE_ID = "SELECT * FROM new_test.date WHERE month = ? AND day = ? AND time = ?;";
    private static final String SQL_GET_NAME_PRICE_BOUGHT_PRODUCTS = "SELECT  new_test.services.name, new_test.services.price\n" +
            "FROM new_test.services\n" +
            "INNER JOIN new_test.users_services ON new_test.users_services.service_id=\n" +
            "new_test.services.service_id WHERE new_test.users_services.user_id = ?;";

    private static final String SQL_GET_DATE_BOUGHT_PRODUCTS = "SELECT  new_test.date.month, new_test.date.day, new_test.date.time\n" +
            "FROM new_test.date\n" +
            "INNER JOIN new_test.users_services ON new_test.date.date_id =\n" +
            "new_test.users_services.date_id WHERE new_test.users_services.user_id = ?;";

    public ArrayList<Stock> getAllBoughtProductsFromProductDAO(Integer user_id) {
        ArrayList<Stock> boughtStocks = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = databaseConnection.dbConnection.prepareStatement(SQL_GET_NAME_PRICE_BOUGHT_PRODUCTS);
            preparedStatement.setInt(1, user_id);
            ResultSet result = preparedStatement.executeQuery();
            PreparedStatement preparedStatement1 = databaseConnection.dbConnection.prepareStatement(SQL_GET_DATE_BOUGHT_PRODUCTS);
            preparedStatement1.setInt(1, user_id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (result.next() && resultSet.next()) {
                boughtStocks.add(new Stock(
                        result.getString("name"),
                        result.getInt("price"),
                        resultSet.getInt("month"),
                        resultSet.getInt("day"),
                        resultSet.getString("time")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return boughtStocks;
    }

    public ArrayList<Stock> getAllStockssFromStockDAO() {
        ResultSet result = databaseConnection.getResultSet(SQL_GET_ALL_STOCKS);
        ArrayList<Stock> stocks = new ArrayList<>();
        try {
            while (result.next()) {
                stocks.add(new Stock(
                        result.getInt("service_id"),
                        result.getString("name"),
                        result.getInt("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stocks;
    }

    public int price(Integer userChoice) {
        int price = 0;
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(PRICE);
            preparedStatement.setInt(1, userChoice);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                price = resultSet.getInt("price");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return price;
    }

    public boolean checkTime(String time, Integer day, Integer month) {
        boolean isExist;
        try {
            PreparedStatement preparedStatement = databaseConnection.dbConnection.prepareStatement(SQL_CHECK_TIME);
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, day);
            preparedStatement.setString(3, time);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExist = !resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExist;
    }

    public void createDate(Integer date_id, String month, String day, Integer time, Integer user_id) {

        try {
            PreparedStatement prSt = databaseConnection.getDbConnection().prepareStatement(SQL_CREATE_DATE);
            prSt.setInt(1, date_id);
            prSt.setString(2, month);
            prSt.setString(3, day);
            prSt.setInt(4, time);
            prSt.setInt(5, user_id);


            prSt.executeUpdate();
            System.out.println("successful connection");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int service_id(Integer month, Integer day, String time) {
        int service_id = 0;
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(DATE_ID);
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, day);
            preparedStatement.setString(3, time);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                service_id = resultSet.getInt("date_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return service_id;
    }
}
