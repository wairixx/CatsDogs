package org.example.services;


import org.example.database.UserDAO;
import org.example.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserService userService;

    @Mock
    private ConsoleService consoleServiceMock;
    @Mock
    private UserDAO userDAOMock;

    private final String INPUT_LOGIN_MESSAGE = "Input Login: ";
    private final String TEST_LOGIN = "vova123";
    private final String INPUT_PASSWORD_MESSAGE = "Input Password: ";
    private final String TEST_PASSWORD = "123";
    private final int TEST_ID = 1;
    private final int TEST_INPUT_MONEY = 300;
    private final int TEST_MONEY = 0;
    User user;

    @BeforeEach
    private void init() {
        userService = new UserService(consoleServiceMock, userDAOMock);
        user = new User(TEST_ID, TEST_LOGIN, TEST_PASSWORD, TEST_MONEY);
    }

    @Test
    public void logInSuccess() {
        when(consoleServiceMock.readStringFromConsole(INPUT_LOGIN_MESSAGE)).thenReturn(TEST_LOGIN);
        when(consoleServiceMock.readStringFromConsole(INPUT_PASSWORD_MESSAGE)).thenReturn(TEST_PASSWORD);
        when(userDAOMock.login(TEST_LOGIN, TEST_PASSWORD)).thenReturn(user);
        User user = userService.logIn();


        assertNotNull(user);
        assertEquals(TEST_ID, user.getId());
        assertEquals(TEST_LOGIN, user.getLogin());
        assertEquals(TEST_PASSWORD, user.getPassword());
    }

    @Test
    public void topUpAccountTest() {
        when(userDAOMock.money(user.getId())).thenReturn(TEST_MONEY);
        when(consoleServiceMock.readNumberFromConsole(1, 2)).thenReturn(1);
        when(consoleServiceMock.readIntFromConsole()).thenReturn(TEST_INPUT_MONEY);
        userService.topUpAccount(user);

        assertEquals(TEST_INPUT_MONEY, user.getMoney());
    }

    @Test
    public void getUserInfoTest() {
        when(userDAOMock.getUserInfo(user.getId())).thenReturn(getInfo());
        userService.getUserInfo(user);

        assertEquals(user.getId(), getInfo().get(0).getId());
        assertEquals(user.getLogin(), getInfo().get(0).getLogin());
        assertEquals(user.getPassword(), getInfo().get(0).getPassword());
        assertEquals(user.getMoney(), getInfo().get(0).getMoney());
        assertEquals(user.getCity(), getInfo().get(0).getCity());
        assertEquals(user.getCountry(), getInfo().get(0).getCountry());

    }

    private ArrayList<User> getInfo() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(user.getId(), user.getLogin(), user.getPassword(), user.getMoney(), user.getCity(), user.getCountry()));
        return users;
    }
}
