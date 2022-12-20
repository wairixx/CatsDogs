package org.example.services;

import org.example.database.ProductDAO;
import org.example.database.UserDAO;
import org.example.entities.Product;

import java.util.ArrayList;

public class PurchaseService {
 //  private ProductDAO productDAO;
 //  private ConsoleService consoleService;
 //  private UserDAO userDAO;
 //  private UserService userService;


 //  public PurchaseService(ConsoleService consoleService, ProductDAO productDAO, UserDAO userDAO, UserService userService) {
 //      this.consoleService = consoleService;
 //      this.productDAO = productDAO;
 //      this.userDAO = userDAO;
 //      this.userService = userService;
 //  }

 //  public void buyProducts() {
 //      int userChoice;
 //      int quantity;
 //      int id = userService.getUserId();
 //      int userMoney = userDAO.money(id);
 //      int purchasePrice = 0;
 //      ArrayList<Product> products = productDAO.getAllProducts();
 //      int to = products.size();
 //      System.out.println("Write id of product to buy");
 //      userChoice = consoleService.readNumberFromConsole(1, to);
 //      System.out.println("Print quantity of stocks");
 //      quantity = consoleService.readIntFromConsole();

 //      int productQuantity = productDAO.productQuantity(userChoice);
 //      purchasePrice += productDAO.price(userChoice) * quantity;

 //      if (userMoney >= purchasePrice) {
 //          if (productQuantity >= quantity) {
 //              userMoney -= purchasePrice;
 //              productQuantity -= quantity;
 //              productDAO.buyProducts(userService.getUserId(), userChoice, quantity);
 //              userDAO.changeMoney(userMoney, id);
 //              productDAO.changeQuantity(productQuantity, userChoice);
 //              System.out.println("payment was successful");
 //              link:
 //              while (true) {
 //                  System.out.println("Do you want to buy more?");
 //                  consoleService.choiceMenu();
 //                  userChoice = consoleService.readNumberFromConsole(1, 2);
 //                  switch (userChoice) {
 //                      case 1:
 //                          buyProducts();
 //                          break;
 //                      case 2:
 //                          break link;
 //                  }
 //              }
 //          } else {
 //              System.out.println("the product is not available");
 //              link:
 //              while (true) {
 //                  System.out.println("Choose another one?");
 //                  consoleService.choiceMenu();
 //                  userChoice = consoleService.readNumberFromConsole(1, 2);
 //                  switch (userChoice) {
 //                      case 1:
 //                          buyProducts();
 //                          break;
 //                      case 2:
 //                          break link;
 //                  }
 //                  break;
 //              }
 //          }
 //      } else {
 //          System.out.println("you don't have enough money");
 //          System.out.println("Your money:" + userDAO.money(id));
 //          // topUpAccount();
 //          buyProducts();
 //      }
 //  }
}
