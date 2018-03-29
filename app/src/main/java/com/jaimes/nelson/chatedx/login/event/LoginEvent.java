package com.jaimes.nelson.chatedx.login.event;

import com.jaimes.nelson.chatedx.BaseEvent;

/**
 * @author Nelson Jaimes Gonzales on 25/03/2018.
 */

public class LoginEvent extends BaseEvent {
    public static final int SIGN_IN_SUCCESS = 1;
    public static final int SIGN_UP_SUCCESS = 2;
    public static final int SIGN_IN_ERROR = 3;
    public static final int SIGN_UP_ERROR = 4;
    public static final int FAILURE_RECOVERY_SESSION = 5;

    public LoginEvent() {
        super(0, "");
    }
}
