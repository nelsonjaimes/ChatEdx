package com.jaimes.nelson.chatedx.contactslist;

/**
 * Created by NJG_3 on 27/03/2018.
 */

public class ContactsListSessionInteractorImpl implements ContactsListSessionInteractor {
    private ContactsListRepository contactsListRepository;

    public ContactsListSessionInteractorImpl() {
        contactsListRepository = new ContactsListRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        contactsListRepository.changeConnectionStatusUser(online);
    }

    @Override
    public void signOf() {
        contactsListRepository.signOf();
    }
}
