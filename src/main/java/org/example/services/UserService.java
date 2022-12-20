package org.example.services;

import org.example.database.UserDAO;
import org.example.entities.User;

import java.util.ArrayList;

public class UserService {
    private UserDAO userDAO;
    private ConsoleService consoleService;
    private StockService stockService;
    private User user;

    public UserService(ConsoleService consoleService, UserDAO userDAO, StockService stockService) {
        this.consoleService = consoleService;
        this.userDAO = userDAO;
        this.stockService = stockService;
    }

    public void logIn() {
        String login;
        String password;
        int userChoiceNumber;

        while (true) {
            login = consoleService.readStringFromConsole("Input Login: ");
            password = consoleService.readStringFromConsole("Input Password: ");
            userDAO.login(login, password);
            int id = userDAO.id(login, password);
            user = new User(id, login, password);


            if (user == null) {
                consoleService.printRetryLoginMenu();
                userChoiceNumber = consoleService.readNumberFromConsole(1, 2);

                if (userChoiceNumber == 1) {
                    //
                    continue;
                } else if (userChoiceNumber == 2) {
                    //
                }
                break;
            }
            break;
        }
        workWithLoggedUser(user);
    }

    public int getUserId() {
        return user.getId();
    }

    public void workWithLoggedUser(User user) {
        int userChoiceNumber;
        link:
        while (true) {
            consoleService.printLoggedMenu();
            userChoiceNumber = consoleService.readNumberFromConsole(1, 3);
            switch (userChoiceNumber) {
                case 1:
                    for (int i = 0; i < userDAO.getUserInfo(user.getId()).size(); i++) {
                        System.out.println(userDAO.getUserInfo(user.getId()).get(i));
                        break;
                    }

                    break;
                case 2:
                    stockService.showServices();
                    break;
                case 3:
                    //logOrSingUp();
                    break;
            }
        }
    }

    public void signUp() {
        String login;
        String password;
        Integer money = 0;
        Integer userChoice;
        String city;
        String country;
        while (true) {
            login = consoleService.readStringFromConsole("Input login:");
            password = consoleService.readStringFromConsole("Input password:");
            while (true) {
                userDAO.signUpUserWithoutAdditional(login, password, money);
                int id = userDAO.id(login, password);
                while (true) {
                    System.out.println("Do you want to add additional info?");
                    consoleService.choiceMenu();
                    userChoice = consoleService.readNumberFromConsole(1, 2);
                    if (userChoice == 1) {
                        city = consoleService.readStringFromConsole("Input city");
                        country = consoleService.readStringFromConsole("Input country");
                        userDAO.signUpUser(getUserId(), city, country);
                        logIn();
                        break;
                    } else if (userChoice == 2) {
                        logIn();
                        break;
                    } else {
                        System.out.println("Please, write 1 or 2");
                    }
                    break;
                }
                break;
            }
            break;
        }
    }
}
