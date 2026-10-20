package com.classes.model;

import java.util.Map;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

@Entity
@Table(name = "UserPoster")
public class User {

    
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String displayname;
    public String username;
    private String password;
    public String email;

    public User(String username, String displayname, String email, String password) {
        this.username = username;
        this.displayname = displayname;
        this.email = email;
        this.password = password;

    }

    public User(int id, String username, String displayname, String email, String password) {
        this.id = id;
        this.username = username;
        this.displayname = displayname;
        this.email = email;
        this.password = password;
    }

    // getters e setters
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

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
