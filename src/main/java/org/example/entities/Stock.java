package org.example.entities;

import lombok.*;

public class Stock {
    @Getter
    @Setter
    public int service_id;
    @Getter
    @Setter
    public String name;
    @Getter
    @Setter
    public int price;
    @Getter
    private Integer month;
    @Getter
    private Integer day;
    @Getter
    private String time;

    public Stock(int id, String name, int price) {
        this.service_id = id;
        this.name = name;
        this.price = price;
    }

    public Stock(String name, Integer price, Integer month, Integer day, String time) {
        this.name = name;
        this.price = price;
        this.month = month;
        this.day = day;
        this.time = time;
    }

    public String toString1() {
        return "\n" + "name=" + name + "\n" + "price=" + price + "\n" +
                "date=" + day + "." + month + "\n" + "time=" + time;
    }

    @Override
    public String toString() {
        return "\n" + "id=" + service_id + "\n" + "name=" + name + "\n" +
                "price=" + price;
    }
}
