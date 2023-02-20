package org.example.services;

import org.example.database.PurchaseDAO;
import org.example.database.StockDAO;
import org.example.database.UserDAO;
import org.example.entities.Stock;
import org.example.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {
    private StockService stockService;
    private User user;


    @Mock
    private UserDAO userDAOMock;
    @Mock
    private StockDAO stockDAOMock;
    @Mock
    private PurchaseDAO purchaseDAOMock;
    @Mock
    private UserService userServiceMock;
    @Mock
    private ConsoleService consoleServiceMock;


    private final String INPUT_TIME = "Input time";
    private final String TEST_TIME = "9:00";
    private final int TEST_DAY = 1;
    private final int TEST_MONTH = 12;
    private final int TEST_ID = 1;
    private final String TEST_LOGIN = "vova123";
    private final String TEST_PASSWORD = "123";
    private final int TEST_MONEY = 500;


    @BeforeEach
    private void init() {
        stockService = new StockService(consoleServiceMock, userDAOMock, stockDAOMock, purchaseDAOMock, userServiceMock);
        user = new User(TEST_ID, TEST_LOGIN, TEST_PASSWORD, TEST_MONEY);
    }

    @Test
    public void getAllStocks() {
        when(stockDAOMock.getAllStocksFromStockDAO()).thenReturn(getStocks());
        stockService.getAllStocks();

        assertEquals(stockService.getAllStocks().size(), getStocks().size());
    }

    private ArrayList<Stock> getStocks() {
        ArrayList<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1, "Appointment", 40));
        stocks.add(new Stock(2, "Grooming", 50));
        stocks.add(new Stock(3, "Vaccination", 80));
        return stocks;
    }
    //  @Test
    //  public void checkTime(){
    //      for (int i =0; i<=timeListTest().size() -1; i++) {
    //          when(stockDAOMock.checkTime(timeListTest().get(i), TEST_DAY, TEST_MONTH)).thenReturn(true);
    //      }
    //      stockService.checkTime(TEST_DAY, TEST_MONTH);
//
    //      assertEquals(stockService.checkTime(TEST_DAY, TEST_MONTH).size(), timeListTest().size());
    //  }

    private ArrayList<String> timeListTest() {
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
}
