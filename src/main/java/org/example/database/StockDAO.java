package org.example.database;

import org.example.entities.Configs;
import org.example.entities.Stock;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAO extends Configs {
    private final Database databaseConnection;

    public StockDAO(Database databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
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

    public ArrayList<Stock> getAllStocksFromStockDAO() {
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
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(STOCK_PRICE);
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
