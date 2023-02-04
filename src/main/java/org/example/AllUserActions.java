package org.example;

import org.example.entities.User;
import org.example.services.ConsoleService;
import org.example.services.ProductService;
import org.example.services.StockService;
import org.example.services.UserService;

public class AllUserActions {
    private final ConsoleService consoleService;
    private final ProductService productService;
    private final UserService userService;
    private final StockService stockService;

    public AllUserActions(ConsoleService consoleService, ProductService productService, UserService userService, StockService stockService) {
        this.consoleService = consoleService;
        this.productService = productService;
        this.userService = userService;
        this.stockService = stockService;
    }

    //TODO dependency injection
    public void firstMenu() {
        int userChoice;

        while (true) {
            consoleService.printMainMenu();
            userChoice = consoleService.readNumberFromConsole(1, 2);
            switch (userChoice) {
                case 1:
                    User user = userService.logIn();
                    workWithLoggedUser(user);
                    break;
                case 2:
                    userService.signUp();
                    firstMenu();
                    break;
            }
            break;
        }
    }

    public void workWithLoggedUser(User user) {
        int userChoice;
        while (true) {
            consoleService.printLoggedMenu();
            userChoice = consoleService.readNumberFromConsole(1, 4);
            switch (userChoice) {
                case 1:
                    consoleService.printUserInfo(userService.getUserInfo(user));
                    break;
                case 2:
                    chooseService(user);
                    break;
                case 3:
                    userService.topUpAccount(user);
                    break;
                case 4:
                    while (true) {
                        System.out.println("Are you sure?");
                        consoleService.choiceMenu();
                        userChoice = consoleService.readNumberFromConsole(1, 2);
                        switch (userChoice) {
                            case 1:
                                firstMenu();
                                break;
                            case 2:
                                workWithLoggedUser(user);
                                break;
                        }
                        break;
                    }
            }

        }
    }

    public void chooseService(User user) {
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
                            consoleService.printAllProductsToConsole(productService.getAllProducts());
                            productService.buyProducts(user);
                            break;
                        case 2:
                            consoleService.printAllBoughtProductsToConsole(productService.getAllBoughtProducts(user));
                            break;
                        case 3:
                            workWithLoggedUser(user);
                            break;
                    }
                    break;
                }
            } else if (userChoice == 2) {
                while (true) {
                    consoleService.stockMenu();
                    userChoice = consoleService.readNumberFromConsole(1, 3);
                    switch (userChoice) {
                        case 1:
                            consoleService.printAllStocksToConsole(stockService.getAllStocks());
                            stockService.buyService(user);
                            break;
                        case 2:
                            consoleService.printAllBoughtStocksToConsole((stockService.getAllBoughtProducts(user)));
                            break;
                        case 3:
                            workWithLoggedUser(user);
                            break;
                    }
                    break;
                }
            }
            break;
        }
    }
}
