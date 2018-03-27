package com.jaimes.nelson.chatedx.login;

/**
 * Created by NJG_3 on 26/03/2018.
 */

public interface LoginRepository {
    void checkAuthenticationUser();

    void signIn(String email,String password);
}
