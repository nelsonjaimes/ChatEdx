package com.jaimes.nelson.chatedx.contactslist;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.jaimes.nelson.chatedx.contactslist.event.ContactsListEvent;
import com.jaimes.nelson.chatedx.domain.FirebaseHelper;
import com.jaimes.nelson.chatedx.login.entities.User;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Nelson Jaimes Gonzales on 27/03/2018.
 */

public class ContactsListRepositoryImpl implements ContactsListRepository {
    private FirebaseHelper firebaseHelper;
    private ChildEventListener childEventListener;

    public ContactsListRepositoryImpl() {
        firebaseHelper = FirebaseHelper.getInstance();

    }

    @Override
    public void changeConnectionStatusUser(boolean online) {
        firebaseHelper.changeUserConnectionStatus(online);
    }

    @Override
    public void signOf() {
        firebaseHelper.signOff();
    }

    @Override
    public void subscribeEvents() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    updateDataSnaphot(dataSnapshot, ContactsListEvent.CONTACT_ADD);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    updateDataSnaphot(dataSnapshot, ContactsListEvent.CONTACT_CHANGE);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    updateDataSnaphot(dataSnapshot, ContactsListEvent.CONTACT_REMOVE);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
        }
        firebaseHelper.getMyContactsReference().addChildEventListener(childEventListener);
    }

    private void updateDataSnaphot(DataSnapshot dataSnapshot, int type) {
        String key = dataSnapshot.getKey();
        String email = key.replace("_", ".");
        boolean online = (boolean) dataSnapshot.getValue();
        User user = new User(online, email, null);
        event(type, user);
    }

    private void event(int type, User user) {
        ContactsListEvent contactsListEvent = new ContactsListEvent(type, user);
        EventBus eventBus = EventBus.getDefault();
        eventBus.post(contactsListEvent);
    }

    @Override
    public void onDestroyEventListener() {
        childEventListener = null;
    }

    @Override
    public void unSubscribeEvents() {
        if (childEventListener != null) {
            firebaseHelper.getMyUserReference().removeEventListener(childEventListener);
        }
    }
}
