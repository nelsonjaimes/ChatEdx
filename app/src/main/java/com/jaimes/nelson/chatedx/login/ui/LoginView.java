package com.jaimes.nelson.chatedx.login.ui;

/**
 * Created by NJG_3 on 25/03/2018.
 */

public interface LoginView {

    void errorMessage(String message);

    void enableInputs();

    void disableInputs();

    void showProgressBar();

    void hideProgressBar();

    void navigationContactsList();
}
