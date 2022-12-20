package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.services.UserService;

import java.util.ArrayList;

@ToString
@AllArgsConstructor
public class User {

    @Getter
    private int id;
    @Getter
    @Setter
    private String login;
    private Integer money;
    @Getter
    @Setter
    private String password;
    private String city;
    private String country;
    private UserService userService;

    public User (int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }


    public User(int id, String login, String password, Integer money) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.money = money;
    }

    public User(Integer id, String login, String password, Integer money, String city, String country) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.money = money;
        this.city = city;
        this.country = country;

    }

    public User(String login, String password, Integer money) {
        this.login = login;
        this.password = password;

    }


    public User() {

    }


    @Override
    public String toString() {
        return "\n" + "id=" + id + "\n" + "name=" + login + "\n" +
                "password=" + password + "\n" + "money=" + money + "\n" + "city=" + city + "\n" + "country=" + country;
    }
}
