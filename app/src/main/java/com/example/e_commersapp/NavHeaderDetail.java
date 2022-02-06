package com.example.e_commersapp;

public class NavHeaderDetail {
    String username,email,userimg;

    public NavHeaderDetail(String username, String email, String userimg) {
        this.username = username;
        this.email = email;
        this.userimg = userimg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }
}
