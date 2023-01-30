package org.example.database;

import org.example.entities.Product;
import org.example.entities.Stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    private Database databaseConnection;

    public ProductDAO(Database databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM new_test.products;";

    private static final String SQL_CHANGE_PRODUCT_QUANTITY = "UPDATE new_test.products SET quantity = ? WHERE product_id = ?;";
    private static final String QUANTITY = "SELECT quantity FROM new_test.products WHERE product_id = ?;";
    private static final String PRICE = "SELECT price FROM new_test.products WHERE product_id = ?;";
    private static final String SQL_GET_BOUGHT_PRODUCTS = "SELECT  new_test.products.name, new_test.products.price, new_test.users_products.product_quantity" +
            "            FROM new_test.products" +
            "            INNER JOIN new_test.users_products ON new_test.users_products.product_id=" +
            "            new_test.products.product_id WHERE new_test.users_products.user_id = ?;";

    public ArrayList<Product> getAllProductsFromProductDAO() {
        ResultSet result = databaseConnection.getResultSet(SQL_GET_ALL_PRODUCTS);
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
            PreparedStatement preparedStatement = databaseConnection.dbConnection.prepareStatement(SQL_GET_BOUGHT_PRODUCTS);
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

    //  public ArrayList<Product> getAllBoughtProductsFromProductDAO(int id) {
    //      ArrayList<Product> products = new ArrayList<>();
    //      try {
    //          PreparedStatement preparedStatement = databaseConnection.dbConnection.prepareStatement(SQL_GET_ALL_BOUGHT_PRODUCTS);
    //          preparedStatement.setInt(1, id);
    //          ResultSet resultSet = preparedStatement.executeQuery();

    //          if (resultSet.next()) {
    //              products.add(new Product(
    //                      resultSet.getInt("id"),
    //                      resultSet.getString("name"),
    //                      resultSet.getInt("price"),
    //                      resultSet.getInt("quantity")));
    //          } else {
    //              System.out.println("You didn't buy products");
    //          }

    //      } catch (SQLException e) {
    //          throw new RuntimeException(e);
    //      }
    //      return products;
    //  }

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
