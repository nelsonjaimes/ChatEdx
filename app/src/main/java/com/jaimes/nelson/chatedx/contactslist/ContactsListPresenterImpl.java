package com.jaimes.nelson.chatedx.contactslist;

import com.jaimes.nelson.chatedx.contactslist.event.ContactsListEvent;
import com.jaimes.nelson.chatedx.contactslist.ui.ContactsListView;
import com.jaimes.nelson.chatedx.login.entities.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Nelson Jaimes Gonzales on 27/03/2018.
 */

public class ContactsListPresenterImpl implements ContactsListPresenter {

    private ContactsListView contactsListView;
    private ContactsListInteractor contactsListInteractor;
    private ContactsListSessionInteractor contactsListSessionInteractor;
    private EventBus eventBus;

    public ContactsListPresenterImpl(ContactsListView contactsListView) {
        this.eventBus = EventBus.getDefault();
        this.contactsListView = contactsListView;
        this.contactsListInteractor = new ContactsListInteractorImpl();
        this.contactsListSessionInteractor = new ContactsListSessionInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {
        contactsListSessionInteractor.changeConnectionStatus(User.ONLINE);
        contactsListInteractor.subscribeEvents();
    }

    @Override
    public void onPause() {
        contactsListInteractor.unSubscribeEvents();
        contactsListSessionInteractor.changeConnectionStatus(User.OFLINE);
    }

    @Override
    public void onDestroy() {
        contactsListView = null;
        eventBus.unregister(this);
        contactsListInteractor.onDestroyContactsListener();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ContactsListEvent contactsListEvent) {

    }
}
