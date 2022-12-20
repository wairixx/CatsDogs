package org.example.services;

import org.example.database.ProductDAO;
import org.example.database.UserDAO;
import org.example.entities.Product;
import org.example.entities.User;

import java.util.ArrayList;

public class StockService {

    private ProductDAO productDAO;
    private ConsoleService consoleService;
    private UserDAO userDAO;
    private PurchaseService purchaseService;


    public StockService(ConsoleService consoleService, ProductDAO productDAO, UserDAO userDAO) {

        this.consoleService = consoleService;
        this.productDAO = productDAO;
        this.userDAO = userDAO;
       // this.purchaseService = purchaseService;
    }

    public void showServices() {
        int userChoice;
        while (true) {
            System.out.println("Please, choose type of service");
            consoleService.servicesMenu();
            userChoice = consoleService.readNumberFromConsole(1, 2);
            if (userChoice == 1) {
                while (true) {
                    consoleService.shopMenu();
                    userChoice = consoleService.readNumberFromConsole(1, 3);
                    switch (userChoice) {
                        case 1:
                            for (int i = 0; i < productDAO.getAllProducts().size(); i++) {
                                System.out.println(productDAO.getAllProducts().get(i));


                            }
                           // purchaseService.buyProducts();
                            break;
                        case 2:
                            //
                            break;
                        case 3:
                            showServices();
                            break;
                    }
                }
            } else if (userChoice == 2) {
                //
            }
        }
    }
}
