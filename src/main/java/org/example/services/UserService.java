package org.example.services;

import org.example.database.UserDAO;
import org.example.entities.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    private final UserDAO userDAO;
    private final ConsoleService consoleService;

    public UserService(ConsoleService consoleService, UserDAO userDAO) {
        this.consoleService = consoleService;
        this.userDAO = userDAO;
    }

    public User logIn() {
        User user;
        String login;
        String password;
        int userChoiceNumber;

        while (true) {
            login = consoleService.readStringFromConsole("Input Login: ");
            password = consoleService.readStringFromConsole("Input Password: ");
            user = userDAO.login(login, password);

            if (user == null) {
                consoleService.printRetryLoginMenu();
                userChoiceNumber = consoleService.readNumberFromConsole(1, 2);

                if (userChoiceNumber == 1) {
                    continue;
                } else {
                    signUp();
                }
                break;
            }
            break;
        }
        return user;
    }
    public void signUp() {
        String login;
        String password;
        Integer money = 0;
        int userChoice;
        String city;
        String country;
        while (true) {
            login = consoleService.readStringFromConsole("Input Login: ");
            checkAccount(login);
            password = consoleService.readStringFromConsole("Input Password: ");
            while (true) {

                userDAO.signUpUserWithoutAdditional(login, password, money);
                int id = userDAO.id(login, password);
                while (true) {
                    consoleService.readStringFromConsole("Do you want to add additional info?");
                    consoleService.choiceMenu();
                    userChoice = consoleService.readNumberFromConsole(1, 2);
                    if (userChoice == 1) {
                        city = consoleService.readStringFromConsole("Input city ");
                        country = consoleService.readStringFromConsole("Input country ");
                        userDAO.signUpUser(id, city, country);
                        break;
                    }
                }
                break;
            }
            break;
        }
    }

    public void checkAccount(String login){
        int userChoice;

        if (userDAO.checkAccount(login)) {
            consoleService.printRetrySignUpMenu();
            userChoice = consoleService.readNumberFromConsole(1, 2);
            switch (userChoice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    logIn();
                    break;
            }

        }
    }
    public ArrayList<User> getUserInfo(User user) {
        return userDAO.getUserInfo(user.getId());
    }

    public void topUpAccount(User user) {
        int userChoice;
        int money;
        int userMoney = userDAO.money(user.getId());
        while (true) {
            consoleService.readStringFromConsole("Your money: " + userDAO.money(user.getId()));
            consoleService.readStringFromConsole("Do you want top up your account?");
            consoleService.choiceMenu();
            userChoice = consoleService.readNumberFromConsole(1, 2);
            if (userChoice == 1) {
                consoleService.readStringFromConsole("Input money");
                money = consoleService.readIntFromConsole();
                int newMoney = userMoney + money;
                user.setMoney(newMoney);
                userDAO.changeMoney(newMoney, user.getId());
                consoleService.readStringFromConsole("Your money:" + user.getMoney());
            }
            break;
        }
    }
}
