package com.jaimes.nelson.chatedx.login;

import com.jaimes.nelson.chatedx.login.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Nelson Jaimes Gonzales on 25/03/2018.
 * @version 1.0
 * @see EventBus
 * @see LoginInteractor
 */

public class LoginInteractorImpl implements LoginInteractor {
    private EventBus eventBus;

    LoginInteractorImpl() {
        eventBus = EventBus.getDefault();
    }

    @Override
    public void checkAuthenticatedUser() {

    }

    @Override
    public void sigIn(String dni, String password) {

    }

    private void event(int type, String message) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setType(type);
        loginEvent.setMessage(message);
        eventBus.post(loginEvent);
    }

}
