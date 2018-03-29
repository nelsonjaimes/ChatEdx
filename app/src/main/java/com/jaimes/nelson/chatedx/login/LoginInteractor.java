package com.jaimes.nelson.chatedx.login;

/**
 * @author Nelson Jaimes Gonzales on 25/03/2018.
 */

public interface LoginInteractor {

    void checkAuthenticatedUser();

    void signIn(String email, String password);

    void signUp(String email, String password);
}
