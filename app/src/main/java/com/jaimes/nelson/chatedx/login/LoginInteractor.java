package com.jaimes.nelson.chatedx.login;

/**
 * @author Nelson Jaimes Gonzales on 25/03/2018.
 */

public interface LoginInteractor {

    void sigIn(String dni,String password);
    void checkAuthenticatedUser();
}
