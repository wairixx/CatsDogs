package org.example.entities;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Getter
    private int id;
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private int money;
    @Getter
    @Setter
    private String password;
    private String city;
    private String country;


    public User(String login, String password, int money) {
        this.login = login;
        this.password = password;
        this.money = money;
    }


    public User(int id, String login, String password, Integer money) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.money = money;
    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(Integer id, String login, String password, Integer money, String city, String country) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.money = money;
        this.city = city;
        this.country = country;

    }

    @Override
    public String toString() {
        return "\n" + "id=" + id + "\n" + "name=" + login + "\n" +
                "password=" + password + "\n" + "money=" + money + "\n" + "city=" + city + "\n" + "country=" + country;
    }
}
