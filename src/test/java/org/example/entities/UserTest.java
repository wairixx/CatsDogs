package org.example.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    private final int TEST_ID = 1;
    private final String TEST_LOGIN = "vova123";
    private final String TEST_PASSWORD = "123";
    private final int TEST_MONEY = 200;
    private final String TEST_CITY = "Vinnytsia";
    private final String TEST_COUNTRY = "Ukraine";

    User user;
    @Test
    public void userTestConstructor1(){
        user = new User(TEST_ID, TEST_LOGIN, TEST_PASSWORD);

        Assertions.assertEquals(user.getId(), TEST_ID);
        Assertions.assertEquals(user.getLogin(), TEST_LOGIN);
        Assertions.assertEquals(user.getPassword(), TEST_PASSWORD);
    }
    @Test
    public void userConstructor2(){
        user = new User(TEST_ID, TEST_LOGIN, TEST_PASSWORD, TEST_MONEY);

        Assertions.assertEquals(user.getId(), TEST_ID);
        Assertions.assertEquals(user.getLogin(), TEST_LOGIN);
        Assertions.assertEquals(user.getPassword(), TEST_PASSWORD);
        Assertions.assertEquals(user.getMoney(), TEST_MONEY);
    }
    @Test
    public void userConstructor3(){
        user = new User(TEST_ID, TEST_LOGIN, TEST_PASSWORD, TEST_MONEY, TEST_CITY, TEST_COUNTRY);

        Assertions.assertEquals(user.getId(), TEST_ID);
        Assertions.assertEquals(user.getLogin(), TEST_LOGIN);
        Assertions.assertEquals(user.getPassword(), TEST_PASSWORD);
        Assertions.assertEquals(user.getMoney(), TEST_MONEY);
        Assertions.assertEquals(user.getCity(), TEST_CITY);
        Assertions.assertEquals(user.getCountry(), TEST_COUNTRY);
    }
}
