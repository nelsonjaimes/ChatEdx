package com.jaimes.nelson.chatedx.login.entities;

import java.util.Map;

/**
 * Created by NJG_3 on 26/03/2018.
 */

public class User {
    public static final boolean ONLINE = true;
    public static final boolean OFLINE = false;
    private Map<String, Boolean> contacts;
    private boolean status;
    private String email;

    public User(boolean status, String email, Map<String, Boolean> contacts) {
        this.contacts = contacts;
        this.status = status;
        this.email = email;
    }
    public User() { }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
