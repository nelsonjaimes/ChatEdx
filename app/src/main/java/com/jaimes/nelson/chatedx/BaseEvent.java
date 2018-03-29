package com.jaimes.nelson.chatedx;

/**
 * Created by NJG_3 on 27/03/2018.
 */

public class BaseEvent {
    private int type;
    private String message;

    public BaseEvent(int type, String message) {
        this.type = type;
        this.message = message;
    }

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
