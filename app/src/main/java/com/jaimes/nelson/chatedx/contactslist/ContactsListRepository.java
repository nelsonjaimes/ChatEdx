package com.jaimes.nelson.chatedx.contactslist;

/**
 * @author Nelson Jaimes Gonzales on 27/03/2018.
 */

public interface ContactsListRepository {
    void subscribeEvents();

    void unSubscribeEvents();

    void changeConnectionStatusUser(boolean online);

    void signOf();

    void onDestroyEventListener();
}
