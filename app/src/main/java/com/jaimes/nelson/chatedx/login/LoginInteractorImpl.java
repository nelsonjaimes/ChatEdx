package com.jaimes.nelson.chatedx.login;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Nelson Jaimes Gonzales on 25/03/2018.
 * @version 1.0
 * @see EventBus
 * @see LoginRepositoryImpl
 */

public class LoginInteractorImpl implements LoginInteractor {
    private LoginRepository loginRepository;

    LoginInteractorImpl() {
        loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void checkAuthenticatedUser() {
        if (loginRepository != null) {
            loginRepository.checkAuthenticationUser();
        }
    }

    @Override
    public void signIn(String email, String password) {
        if (loginRepository != null) {
            loginRepository.signIn(email, password);
        }
    }
}
