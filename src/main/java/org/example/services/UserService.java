package org.example.services;

import org.example.database.UserDAO;
import org.example.entities.Product;
import org.example.entities.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    private UserDAO userDAO;
    private ConsoleService consoleService;

    public UserService(ConsoleService consoleService, UserDAO userDAO) {
        this.consoleService = consoleService;
        this.userDAO = userDAO;
    }

    public User logIn() {
        User user;
        String login;
        String password;
        int money;
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
    //  public void workWithLoggedUser(User user) {
    //      int userChoiceNumber;
    //      link:
    //      while (true) {
    //          consoleService.printLoggedMenu();
    //          userChoiceNumber = consoleService.readNumberFromConsole(1, 3);
    //          switch (userChoiceNumber) {
    //              case 1:
    //                  for (int i = 0; i < userDAO.getUserInfo(user.getId()).size(); i++) {
    //                      System.out.println(userDAO.getUserInfo(user.getId()).get(i));
    //                      break;
    //                  }
    //                  break;
    //              case 2:
    //                  //stockService.showServices();
    //                  break;
    //              case 3:
    //                  logIn();
    //                  break;
    //          }
    //      }
    //  }

    public void signUp() {
        String login;
        String password;
        Integer money = 0;
        Integer userChoice;
        String city;
        String country;
        while (true) {
            login = consoleService.readStringFromConsole("Input login:");
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
                        userDAO.signUpUser(id, city, country);
                        break;
                    } else if (userChoice == 2) {
                        break;
                    }
                }
                break;
            }
            break;
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
            System.out.println("Your money: " + userDAO.money(user.getId()));
            System.out.println("Do you want top up your account?");
            consoleService.choiceMenu();
            userChoice = consoleService.readNumberFromConsole(1, 2);
            if (userChoice == 1) {
                System.out.println("Input money");
                Scanner sc = new Scanner(System.in);
                money = sc.nextInt();
                int newMoney = userMoney + money;
                user.setMoney(newMoney);
                userDAO.changeMoney(newMoney, user.getId());
                System.out.println("Your money:" + user.getMoney());
            } else if (userChoice == 2) {
                break;
            }
            break;
        }
    }
}
