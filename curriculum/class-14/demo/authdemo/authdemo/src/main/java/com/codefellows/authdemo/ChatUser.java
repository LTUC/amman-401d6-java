package com.codefellows.authdemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;

    // This is for Spring
    protected ChatUser()
    {

    }

    public ChatUser(String username, String password)
    {
        this.username = username;
        this.password = password;
    }


}
