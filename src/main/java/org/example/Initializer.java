package org.example;

import lombok.NoArgsConstructor;
import org.example.database.*;
import org.example.entities.User;
import org.example.services.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public class Initializer {
    private UserDAO userDAO;
    private PurchaseDAO purchaseDAO;
    private ConsoleService consoleService;
    private StockDAO stockDAO;
    private DatabaseConnection databaseConnection;
    public UserService userService;
    public ProductDAO productDAO;
    private StockService stockService;
    private ProductService productService;
    private PurchaseService purchaseService;

    public DatabaseConnection getDatabaseConnection() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }


 // public ProductService getProductService() {
 //     if (productService == null) {
 //         productService = new ProductService(getConsoleService(), getProductDAO(), getUserDAO(), getUserService());
 //     }
 //     return productService;
 // }

    public ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAO(getDatabaseConnection());
        }
        return productDAO;
    }

    public ConsoleService getConsoleService() {
        if (consoleService == null) {
            consoleService = new ConsoleService();
        }
        return consoleService;
    }

    public StockService getStockService() {
        if (stockService == null) {
            stockService = new StockService(getConsoleService(), getProductDAO(), getUserDAO());
        }
        return stockService;
    }

    public UserService getUserService() {
        if (userService == null) {
            userService = new UserService(getConsoleService(), getUserDAO(),  getStockService());
        }
        return userService;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO(getDatabaseConnection());
        }
        return userDAO;
    }

    public PurchaseDAO getPurchaseDAO() {
        if (purchaseDAO == null) {
            purchaseDAO = new PurchaseDAO();
        }
        return purchaseDAO;
    }
  //  public PurchaseService getPurchaseService(){
  //      if (productService == null){
  //          purchaseService = new PurchaseService(getConsoleService(), getProductDAO(), getUserDAO(), getUserService());
  //      }
  //      return purchaseService;
  //  }

}
