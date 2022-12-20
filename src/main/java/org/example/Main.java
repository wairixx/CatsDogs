package org.example;

import org.example.database.ProductDAO;
import org.example.database.StockDAO;
import org.example.database.UserDAO;
import org.example.entities.User;
import org.example.services.ConsoleService;
import org.example.services.UserService;

public class Main {
    public static void main(String[] args) {

        Initializer initializer = new Initializer();
        initializer.getConsoleService().printMainMenu();
        int userChoice;
        while (true) {
            userChoice = initializer.getConsoleService().readNumberFromConsole(1, 2);

            switch (userChoice) {
                case 1:
                    initializer.getUserService().logIn();
                    break;
                case 2:
                    initializer.getUserService().signUp();
                    break;
            }
        }
    }
}