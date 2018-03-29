package com.jaimes.nelson.chatedx.contactslist;

import com.jaimes.nelson.chatedx.contactslist.event.ContactsListEvent;

/**
 * Created by NJG_3 on 27/03/2018.
 */

public interface ContactsListPresenter {
    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();

    void onEventMainThread(ContactsListEvent contactsListEvent);

}
