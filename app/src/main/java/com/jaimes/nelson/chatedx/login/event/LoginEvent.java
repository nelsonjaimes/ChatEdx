package com.jaimes.nelson.chatedx.login.event;

/**
 * @author Nelson Jaimes Gonzales on 25/03/2018.
 */

public class LoginEvent {
    public static final int LOGIN_SUCCESS = 1;
    private int type;
    private String message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
