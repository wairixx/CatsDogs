package org.example.database;

import org.example.entities.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    private DatabaseConnection databaseConnection;
    public ProductDAO(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }
    private static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM new_test.products;";
    private static final String SQL_BUY_PRODUCT = "INSERT INTO new_test.users_products ( user_id, product_id, product_quantity) VALUES (?,?,?)";
    private static final String SQL_CHANGE_PRODUCT_QUANTITY = "UPDATE new_test.products SET quantity = ? WHERE id = ?";
    private static final String MONEY = "SELECT money FROM new_test.users WHERE id = ?;";
    private static final String QUANTITY = "SELECT quantity FROM new_test.products WHERE id = ?;";
    private static final String PRICE = "SELECT price FROM new_test.products WHERE id = ?;";
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(SQL_GET_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery(SQL_GET_ALL_PRODUCTS);
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public void buyProducts(Integer id, Integer productId, Integer quantity) {
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(SQL_BUY_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public int productQuantity(Integer userChoice) {
        int quantity = 0;
        try {
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(QUANTITY);
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
            PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(SQL_CHANGE_PRODUCT_QUANTITY);
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
}
