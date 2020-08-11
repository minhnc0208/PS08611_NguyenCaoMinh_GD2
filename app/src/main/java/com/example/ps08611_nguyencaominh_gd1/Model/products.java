package com.example.ps08611_nguyencaominh_gd1.Model;

public class products {
    public String id;
    public String name;
    public Double price;
    public String description;

    public products() {
    }

    public products(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public products(String id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
