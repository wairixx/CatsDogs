package org.example.services;

import org.example.database.ProductDAO;
import org.example.database.PurchaseDAO;
import org.example.database.UserDAO;
import org.example.entities.Product;
import org.example.entities.User;

import java.util.ArrayList;

public class PurchaseService {
    private ProductDAO productDAO;
    private ConsoleService consoleService;
    private UserDAO userDAO;
    private UserService userService;
    private PurchaseDAO purchaseDAO;


    public PurchaseService(ConsoleService consoleService, ProductDAO productDAO, UserDAO userDAO, UserService userService, PurchaseDAO purchaseDAO) {
        this.consoleService = consoleService;
        this.productDAO = productDAO;
        this.purchaseDAO = purchaseDAO;
        this.userDAO = userDAO;
        this.userService = userService;
    }

}


