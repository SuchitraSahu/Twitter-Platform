package com.h2.oop.twitterplatform.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String email;
    private String name;
    private String password;

    public User()
    {

    }

    public User(String email, String name, String password)
    {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public int getUserID()
    {
        return userID;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}



