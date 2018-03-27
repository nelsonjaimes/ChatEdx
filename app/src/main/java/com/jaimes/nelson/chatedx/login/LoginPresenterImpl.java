package com.jaimes.nelson.chatedx.login;

import com.jaimes.nelson.chatedx.login.event.LoginEvent;
import com.jaimes.nelson.chatedx.login.ui.LoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by NJG_3 on 25/03/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private EventBus eventBus;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.eventBus = EventBus.getDefault();
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void signIn() {

    }

    @Override
    public void checkAuthenticatedUser() {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgressBar();
        }
        loginInteractor.checkAuthenticatedUser();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LoginEvent loginEvent) {
        if (loginView != null) {
            loginView.showUser(loginEvent.getMessage());
        }
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }
}