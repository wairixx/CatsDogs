package org.example.database;

import org.example.entities.Configs;
import org.example.entities.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO{
    private final Database databaseConnection;


    public ProductDAO(Database databaseConnection) {
        this.databaseConnection = databaseConnection;
    }




    public ArrayList<Product> getAllProductsFromProductDAO() {
        ResultSet result = databaseConnection.getResultSet(Configs.SQL_GET_ALL_PRODUCTS);
        ArrayList<Product> products = new ArrayList<>();
        try {
            while (result.next()) {
                products.add(new Product(
                        result.getInt("product_id"),
                        result.getString("name"),
                        result.getInt("price"),
                        result.getInt("quantity")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public ArrayList<Product> getAllBoughtProductsFromProductDAO(Integer user_id) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = databaseConnection.dbConnection.prepareStatement(Configs.SQL_GET_BOUGHT_PRODUCTS);
            preparedStatement.setInt(1, user_id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                products.add(new Product(
                        result.getString("name"),
                        result.getInt("price"),
                        result.getInt("product_quantity")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public int productQuantity(Integer userChoice) {
        int quantity = 0;
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(Configs.QUANTITY);
            preparedStatement.setInt(1, userChoice);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return quantity;
    }

    public void changeQuantity(Integer quantity, Integer userChoice) {
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(Configs.SQL_CHANGE_PRODUCT_QUANTITY);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, userChoice);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int price(Integer userChoice) {
        int price = 0;
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(Configs.PRICE);
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
}
