package com.classes;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


@Entity 
@Table (name = "users")
public class User 
{
    public User(){}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String displayName;
    
    @NotNull(message = "Choose an username.")
    public String username;
    @NotNull(message = "Choose a password.")
    private String password;
    @Email(message = "This email is not valid.")
    public String email;
   

    public User(String username, String email, String password, String displayName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
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

    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

     public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
