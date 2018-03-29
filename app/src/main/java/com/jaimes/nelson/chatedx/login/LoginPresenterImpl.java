package com.jaimes.nelson.chatedx.login;

import android.content.Context;
import android.support.annotation.StringRes;

import com.jaimes.nelson.chatedx.R;
import com.jaimes.nelson.chatedx.login.event.LoginEvent;
import com.jaimes.nelson.chatedx.login.ui.LoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Nelson Jaimes Gonzales on 25/03/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private EventBus eventBus;
    private Context context;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.context = (Context) loginView;
        this.eventBus = EventBus.getDefault();
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void signIn(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return;
        }
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgressBar();
        }
        loginInteractor.signIn(email, password);
    }

    @Override
    public void signUp(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return;
        }
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgressBar();
        }
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
        switch (loginEvent.getType()) {
            case LoginEvent.SIGN_IN_ERROR:
                onErrorSignIn(loginEvent.getMessage());
                break;
            case LoginEvent.SIGN_IN_SUCCESS:
                onSuccessSignIn();
                break;
            case LoginEvent.FAILURE_RECOVERY_SESSION:
                onFailureRecoverySession();
                break;
            case LoginEvent.SIGN_UP_SUCCESS:
                onSuccessSignUp();
                break;
            case LoginEvent.SIGN_UP_ERROR:
                onErrorSignUp(loginEvent.getMessage());
                break;
        }
    }

    private void onSuccessSignUp() {
        if (loginView != null) {
            loginView.navigationContactsList();
        }
    }

    private void onErrorSignUp(String message) {
        if (loginView != null) {
            loginView.hideProgressBar();
            loginView.enableInputs();
            if (message == null) message = getString(R.string.onSigUpError);
            loginView.errorMessage(message);
        }
    }

    private void onErrorSignIn(String message) {
        if (loginView != null) {
            loginView.hideProgressBar();
            loginView.enableInputs();
            if (message == null) message = getString(R.string.onSigInError);
            loginView.errorMessage(message);
        }
    }

    private void onFailureRecoverySession() {
        if (loginView != null) {
            loginView.errorMessage(getString(R.string.onFailureRecoverySession));
            loginView.hideProgressBar();
            loginView.enableInputs();
        }
    }

    private void onSuccessSignIn() {
        if (loginView != null) {
            loginView.navigationContactsList();
        }
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        loginView = null;
    }

    private String getString(@StringRes int stringId) {
        return context.getResources().getString(stringId);
    }
}