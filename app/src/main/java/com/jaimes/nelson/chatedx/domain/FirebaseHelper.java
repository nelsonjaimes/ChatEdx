package com.jaimes.nelson.chatedx.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaimes.nelson.chatedx.login.entities.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NJG_3 on 25/03/2018.
 */

public class FirebaseHelper {

    private DatabaseReference databaseReference;
    private static final String USERS_PATH = "user";
    private static final String CHATS_PATH = "chats";
    private static final String CONTACTS_PATH = "contacts";
    private static final String SEPARATOR = "__";

    public FirebaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private static class SingleFireBaseHelper {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance() {
        return SingleFireBaseHelper.INSTANCE;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public DatabaseReference getMyUserReference() {
        return getUserReference(getMyUser());
    }

    public String getMyUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = null;
        if (firebaseUser != null) {
            email = firebaseUser.getEmail();
        }
        return email;
    }

    public DatabaseReference getUserReference(String email) {
        DatabaseReference userReference = null;
        if (email != null && !email.isEmpty()) {
            String emailKey = email.replace(".", "_");
            userReference = databaseReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

    public DatabaseReference getContactsReference(String email) {
        return getUserReference(email).child(CONTACTS_PATH);
    }

    public DatabaseReference getMyContactsReference() {
        return getContactsReference(getMyUser());
    }

    public DatabaseReference getOneContactReference(String email, String childEmail) {
        String childKey = childEmail.replace(".", "_");
        return getContactsReference(email).child(childKey);
    }

    private void notifyChangeConnectionAllContacts(final boolean online, final boolean singOff) {
        final String myEmail = getMyUser();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot contactSnapshot : dataSnapshot.getChildren()) {
                    String email = contactSnapshot.getKey();
                    DatabaseReference contactsReference = getOneContactReference(email, myEmail);
                    contactsReference.setValue(online);
                }
                if (singOff) {
                    FirebaseAuth.getInstance().signOut();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void notifyChangeConnectionAllContacts(boolean online) {
        notifyChangeConnectionAllContacts(online, false);
    }

    public void changeUserConnectionStatus(boolean online) {
        DatabaseReference myUserReference = getMyUserReference();
        if (myUserReference != null) {
            Map<String, Object> statusMap = new HashMap<>();
            statusMap.put(TreeHelper.ONLINE, online);
            myUserReference.updateChildren(statusMap);
            notifyChangeConnectionAllContacts(User.ONLINE);
        }
    }
}
