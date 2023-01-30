package org.example.services;

import org.example.Initializer;
import org.example.database.UserDAO;
import org.example.entities.Product;
import org.example.entities.Stock;
import org.example.entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleService {
    private Initializer initializer = new Initializer();

    public ConsoleService() {
    }

    private ConsoleService consoleService;

    public ConsoleService(Initializer initializer) {
        consoleService = initializer.getConsoleService();
    }

    private static Scanner scanner = new Scanner(System.in);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void printMainMenu() {
        System.out.println("1-login to account");
        System.out.println("2-sign up");
    }

    public void choiceMenu() {
        System.out.println("1-yes");
        System.out.println("2-no");
    }

    public void servicesMenu() {
        System.out.println("1-shop");
        System.out.println("2-treatment");
    }

    public void shopMenu() {
        System.out.println("1-show products");
        System.out.println("2-show bought products");
        System.out.println("3-exit");
    }

    public void stockMenu() {
        System.out.println("1-show stocks");
        System.out.println("2-show my stocks");
        System.out.println("3-exit");
    }

    public void serviceMenu() {
        System.out.println("1-choose another time");
        System.out.println("2-choose another day");
    }

    public void printLoggedMenu() {
        System.out.println("1-show info");
        System.out.println("2-show services");
        System.out.println("3-top your account");
        System.out.println("4-exit");
    }

    public void printRetryLoginMenu() {
        System.out.println("1-try another login or password");
        System.out.println("2-signUp");
    }

    public void printRetrySignUpMenu() {
        System.out.println("1-try another login");
        System.out.println("2-logIn");
    }

    public String readStringFromConsole() {
        return scanner.nextLine();
    }

    public Integer readIntFromConsole() {
        return scanner.nextInt();
    }

    public String readStringFromConsole(String message) {
        System.out.println(message);
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return line;
    }

    public int readNumberFromConsole(int from, int to) {
        int fromCopy = from;
        int number;
        while (true) {
            try {
                from = fromCopy;
                number = Integer.parseInt(scanner.next());
                for (; from <= to; from++) {
                    if (number == from) {
                        return number;
                    }
                }
                System.out.println("Please input number between " + fromCopy + " and " + to);
            } catch (Exception e) {
                System.out.print("Please input positive valid number: ");
                continue;
            }
        }
    }

    public void printAllProductsToConsole(ArrayList<Product> products) {
        products.forEach((x) -> System.out.println(x.toString()));
    }

    public void printAllBoughtProductsToConsole(ArrayList<Product> products) {
        products.forEach((x) -> System.out.println(x.toString1()));
    }

    public void printAllTimeToConsole(ArrayList<String> avilableTime) {
        avilableTime.forEach((x) -> System.out.println(x.toString()));
    }

    public void printAllStocksToConsole(ArrayList<Stock> stocks) {
        stocks.forEach((x) -> System.out.println(x.toString()));
    }

    public void printAllBoughtStocksToConsole(ArrayList<Stock> stocks) {
        stocks.forEach((x) -> System.out.println(x.toString1()));
    }

    public void printUserInfo(ArrayList<User> users) {
        users.forEach((x) -> System.out.println(x.toString()));
    }
}

