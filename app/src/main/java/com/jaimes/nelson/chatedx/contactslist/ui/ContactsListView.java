package com.jaimes.nelson.chatedx.contactslist.ui;

import com.jaimes.nelson.chatedx.login.entities.User;

/**
 * Created by NJG_3 on 27/03/2018.
 */

public interface ContactsListView {
    void onContactAdd(User user);

    void onContactRemove(User user);

    void onContactChange(User user);
}
