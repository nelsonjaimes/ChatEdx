package com.jaimes.nelson.chatedx.login;

import com.jaimes.nelson.chatedx.login.event.LoginEvent;

/**
 * Created by NJG_3 on 25/03/2018.
 */

public interface LoginPresenter {
    void onCreate();

    void onDestroy();

    void signIn(String email, String password);

    void onEventMainThread(LoginEvent loginEvent);

    void checkAuthenticatedUser();
}
