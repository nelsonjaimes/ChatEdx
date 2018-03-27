package com.jaimes.nelson.chatedx.login.event;

/**
 * @author Nelson Jaimes Gonzales on 25/03/2018.
 */

public class LoginEvent {
    public static final int SIGN_IN_SUCCESS = 1;
    public static final int SIGN_IN_ERROR = 2;
    public static final int FAILURE_RECOVERY_SESSION = 3;
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
