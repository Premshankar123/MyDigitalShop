package com.example.e_commersapp;

public class HarizantalProductModal {
    String name, price, imageurl;

    public HarizantalProductModal(String name, String price, String imageurl) {
        this.name = name;
        this.price = price;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}