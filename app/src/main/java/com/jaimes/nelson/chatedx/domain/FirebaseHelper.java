package com.jaimes.nelson.chatedx.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    private String getMyUser() {
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
}
