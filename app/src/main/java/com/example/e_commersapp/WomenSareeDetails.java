package com.example.e_commersapp;

public class WomenSareeDetails {
    String name,price,imageurl,about_pct,pct_off;

    public WomenSareeDetails(String name, String price, String imageurl, String about_pct, String pct_off) {
        this.name = name;
        this.price = price;
        this.imageurl = imageurl;
        this.about_pct = about_pct;
        this.pct_off = pct_off;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getAbout_pct() {
        return about_pct;
    }

    public String getPct_off() {
        return pct_off;
    }
}
