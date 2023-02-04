package org.example.entities;

import lombok.Getter;
import lombok.Setter;

public class Product {
    @Getter
    @Setter
    public int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int price;
    @Getter
    @Setter
    private int quantity;
    @Getter
    @Setter
    private Integer typeId;


    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString1() {
        return "\n" + "name=" + name + "\n" +
                "price=" + price + "\n" + "quantity=" + quantity;
    }

    @Override
    public String toString() {
        return "\n" + "id=" + id + "\n" + "name=" + name + "\n" +
                "price=" + price;
    }
}