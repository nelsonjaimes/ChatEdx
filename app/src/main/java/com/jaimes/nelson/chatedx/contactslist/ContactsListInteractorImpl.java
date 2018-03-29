package com.jaimes.nelson.chatedx.contactslist;

/**
 * Created by NJG_3 on 27/03/2018.
 */

public class ContactsListInteractorImpl implements ContactsListInteractor {

    private ContactsListRepository contactsListRepository;

    public ContactsListInteractorImpl() {
        contactsListRepository = new ContactsListRepositoryImpl();
    }

    @Override
    public void subscribeEvents() {
        contactsListRepository.subscribeEvents();
    }

    @Override
    public void unSubscribeEvents() {
        contactsListRepository.unSubscribeEvents();
    }

    @Override
    public void onDestroyContactsListener() {
        contactsListRepository.onDestroyEventListener();
    }
}
