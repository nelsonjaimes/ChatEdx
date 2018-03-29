package com.jaimes.nelson.chatedx.contactslist;

/**
 * Created by NJG_3 on 27/03/2018.
 */

public interface ContactsListInteractor {
    void subscribeEvents();

    void unSubscribeEvents();

    void onDestroyContactsListener();

}
