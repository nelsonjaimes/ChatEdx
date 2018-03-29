package com.jaimes.nelson.chatedx.contactslist.event;

import com.jaimes.nelson.chatedx.BaseEvent;
import com.jaimes.nelson.chatedx.login.entities.User;

/**
 * @author Nelson Jaimes Gonzales on 27/03/2018.
 */

public class ContactsListEvent extends BaseEvent {
    public static final int CONTACT_ADD = 1;
    public static final int CONTACT_REMOVE = 2;
    public static final int CONTACT_CHANGE = 3;
    private User user;

    public ContactsListEvent(int type, User user) {
        super(type, "");
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
