package org.example.services;

import org.example.database.ProductDAO;
import org.example.database.PurchaseDAO;
import org.example.database.UserDAO;
import org.example.entities.Product;
import org.example.entities.Stock;
import org.example.entities.User;

import java.util.ArrayList;

public class ProductService {
    private ProductDAO productDAO;
    private ConsoleService consoleService;
    private UserDAO userDAO;
    private UserService userService;
    private PurchaseDAO purchaseDAO;


    public ProductService(ConsoleService consoleService, ProductDAO productDAO, UserDAO userDAO, UserService userService, PurchaseDAO purchaseDAO) {
        this.consoleService = consoleService;
        this.productDAO = productDAO;
        this.purchaseDAO = purchaseDAO;
        this.userDAO = userDAO;
        this.userService = userService;
    }

    public ArrayList<Product> getAllProducts() {
        return productDAO.getAllProductsFromProductDAO();
    }

    public ArrayList<Product> getAllBoughtProducts(User user) {
        return productDAO.getAllBoughtProductsFromProductDAO(user.getId());
    }

    //  public ArrayList<Product> getAllBoughtProductFromProductDAO(User user) {
    //      return productDAO.getAllBoughtProductsFromProductDAO(user.getId());
    //  }

    // public void showProducts() {
    //     for (int i = 0; i < productDAO.getAllProducts().size(); i++) {
    //         System.out.println(productDAO.getAllProducts().get(i));
    //     }
    // }
    public void buyProducts(User user) {
        int userMoney = userDAO.money(user.getId());
        int userChoice;
        ArrayList<Product> products = productDAO.getAllProductsFromProductDAO();
        int to = products.size();
        a:
        while (true) {
            System.out.println("Do you want to buy a product?");
            consoleService.choiceMenu();
            userChoice = consoleService.readNumberFromConsole(1, 2);
            if (userChoice == 1) {
                System.out.println("Write id of product to buy");
                userChoice = consoleService.readNumberFromConsole(1, to);
                System.out.println("Print quantity of stocks");
                int quantity = consoleService.readIntFromConsole();
                int productsPrice = productDAO.price(userChoice) * quantity;
                int productQuantity = productDAO.productQuantity(userChoice);

                if (userMoney >= productsPrice) {
                    if (productQuantity >= quantity) {
                        purchaseDAO.buyProduct(userChoice, user.getId(), quantity);
                        int newMoney = userMoney - productsPrice;
                        user.setMoney(newMoney);
                        userDAO.changeMoney(newMoney, user.getId());
                        productDAO.changeQuantity((productDAO.productQuantity(userChoice) - quantity), userChoice);
                        System.out.println("payment was successful");
                        break;
                    } else {
                        System.out.println("the product is not available");
                        link:
                        while (true) {
                            System.out.println("Choose another one?");
                            consoleService.choiceMenu();
                            userChoice = consoleService.readNumberFromConsole(1, 2);
                            switch (userChoice) {
                                case 1:
                                    buyProducts(user);
                                    break;
                                case 2:
                                    break link;
                            }
                            break;
                        }
                    }
                } else {
                    System.out.println("you don't have enough money");
                    userService.topUpAccount(user);
                    buyProducts(user);
                }
                break;
            } else {
                break a;
            }
        }

    }
}
