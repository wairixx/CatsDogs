package org.example;

import lombok.NoArgsConstructor;
import org.example.database.*;
import org.example.services.*;

@NoArgsConstructor
public class Initializer {
    private UserDAO userDAO;
    private PurchaseDAO purchaseDAO;
    private ConsoleService consoleService;
    private StockDAO stockDAO;
    private Database databaseConnection;
    public UserService userService;
    public ProductDAO productDAO;
    private StockService stockService;
    private ProductService productService;
    private AllUserActions allUserActions;



    public Database getDatabaseConnection() {
        if (databaseConnection == null) {
            databaseConnection = new Database();
        }
        return databaseConnection;
    }

    public AllUserActions getAllUserActions() {
        if (allUserActions == null) {
            allUserActions = new AllUserActions(getConsoleService(), getProductService(), getUserService(), getStockService());
        }
        return allUserActions;
    }
    public StockDAO getStockDAO() {
        if (stockDAO == null) {
            stockDAO = new StockDAO(getDatabaseConnection());
        }
        return stockDAO;
    }


    public ProductService getProductService() {
        if (productService == null) {
            productService = new ProductService(getConsoleService(), getProductDAO(), getUserDAO(), getUserService(), getPurchaseDAO());
        }
        return productService;
    }

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
            stockService = new StockService(getConsoleService(), getUserDAO(), getStockDAO(), getPurchaseDAO(), getUserService());
        }
        return stockService;
    }

    public UserService getUserService() {
        if (userService == null) {
            userService = new UserService(getConsoleService(), getUserDAO());
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
            purchaseDAO = new PurchaseDAO(getDatabaseConnection());
        }
        return purchaseDAO;
    }
}
