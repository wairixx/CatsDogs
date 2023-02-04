package org.example.services;

import org.example.database.PurchaseDAO;
import org.example.database.StockDAO;
import org.example.database.UserDAO;
import org.example.entities.Stock;
import org.example.entities.User;

import java.util.ArrayList;

public class StockService {
    private final ConsoleService consoleService;
    private final UserDAO userDAO;
    private final StockDAO stockDAO;
    private final PurchaseDAO purchaseDAO;
    private final UserService userService;


    public StockService(ConsoleService consoleService,UserDAO userDAO,
                        StockDAO stockDAO, PurchaseDAO purchaseDAO, UserService userService) {

        this.consoleService = consoleService;
        this.userDAO = userDAO;
        this.stockDAO = stockDAO;
        this.purchaseDAO = purchaseDAO;
        this.userService = userService;
    }

    public ArrayList<Stock> getAllStocks() {
        return stockDAO.getAllStocksFromStockDAO();
    }

    public ArrayList<Stock> getAllBoughtProducts(User user) {
        return stockDAO.getAllBoughtProductsFromProductDAO(user.getId());
    }

    public ArrayList<String> timeList() {
        ArrayList<String> time = new ArrayList<>();
        time.add(0, "9:00");
        time.add(1, "10:00");
        time.add(2, "11:00");
        time.add(3, "12:00");
        time.add(4, "13:00");
        time.add(5, "14:00");
        time.add(6, "15:00");
        time.add(7, "16:00");
        time.add(8, "17:00");
        time.add(9, "18:00");
        return time;
    }

    public ArrayList<String> checkTime(Integer day, Integer month) {
        ArrayList<String> availableTime = new ArrayList<>();
        for (int i = 0; i <= timeList().size() - 1; i++) {
            if (stockDAO.checkTime(timeList().get(i), day, month)) {
                availableTime.add(timeList().get(i));
            }
        }
        return availableTime;
    }

    public void buyService(User user) {
        int userChoice;
        ArrayList<Stock> stocks = stockDAO.getAllStocksFromStockDAO();
        int to = stocks.size();
        while (true) {
            System.out.println("Do you want to buy a service?");
            consoleService.choiceMenu();
            userChoice = consoleService.readNumberFromConsole(1, 2);
            if (userChoice == 1) {
                int month;
                int day;
                String time;
                System.out.println("Write id of service to buy");
                userChoice = consoleService.readNumberFromConsole(1, to);
                System.out.println("Write number of month");
                month = consoleService.readNumberFromConsole(1, 12);
                System.out.println("Write number of day");
                while (true) {
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 11) {
                        day = consoleService.readNumberFromConsole(1, 31);
                    } else if (month == 2) {
                        day = consoleService.readNumberFromConsole(1, 28);
                    } else {
                        day = consoleService.readNumberFromConsole(1, 30);
                    }
                    break;
                }
                System.out.println("Available time: ");
                consoleService.printAllTimeToConsole(checkTime(day, month));
                time = consoleService.readStringFromConsole("Input time");
                int servicePrice = stockDAO.price(userChoice);
                int userMoney = userDAO.money(user.getId());
                if (userMoney >= servicePrice) {
                    purchaseDAO.makeDate(month, day, time, user.getId());
                    int date_id = stockDAO.service_id(month, day, time);
                    purchaseDAO.buyService(user.getId(), userChoice, date_id);
                    int newMoney = userMoney -= servicePrice;
                    user.setMoney(newMoney);
                    userDAO.changeMoney(newMoney, user.getId());
                } else {
                    System.out.println("you don't have enough money");
                    userService.topUpAccount(user);
                    break;
                }
            } else {
                break;
            }
            break;
        }
    }
}
