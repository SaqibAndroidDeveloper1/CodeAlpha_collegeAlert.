package com.example.collegealert;

import java.util.List;

public class Users {

    String username;
    String email;
    String campusname;
    String password;


    public String getCampusname() {
        return campusname;
    }

    public void setCampusname(String campusname) {
        this.campusname = campusname;
    }

    public Users(String username, String email, String campusname, String password){

        this.username=username;
        this.password=password;
        this.email=email;
         this.campusname=campusname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
