package com.jaimes.nelson.chatedx.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jaimes.nelson.chatedx.domain.FirebaseHelper;
import com.jaimes.nelson.chatedx.login.entities.User;
import com.jaimes.nelson.chatedx.login.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Nelson Jaimes Gonzales on 26/03/2018.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper firebaseHelper;
    private DatabaseReference myUserReference;

    LoginRepositoryImpl() {
        firebaseHelper = FirebaseHelper.getInstance();
        myUserReference = firebaseHelper.getMyUserReference();
    }

    @Override
    public void checkAuthenticationUser() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            myUserReference = firebaseHelper.getMyUserReference();
            myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    signIn(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    event(LoginEvent.SIGN_IN_ERROR, databaseError.getMessage());
                }

            });
        } else {
            event(LoginEvent.FAILURE_RECOVERY_SESSION);
        }
    }

    @Override
    public void signIn(String email, String password) {
        try {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(email, password).
                    addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            myUserReference = firebaseHelper.getMyUserReference();
                            myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    signIn(dataSnapshot);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    event(LoginEvent.SIGN_IN_ERROR, databaseError.getMessage());
                                }
                            });
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    event(LoginEvent.SIGN_IN_ERROR, e.getMessage());
                }
            });
        } catch (Exception e) {
            event(LoginEvent.SIGN_IN_ERROR);
        }
    }

    private void signUp() {
        String myEmail = firebaseHelper.getMyUser();
        if (myEmail != null) {
            User user = new User(User.ONLINE, myEmail, null);
            myUserReference.setValue(user);
        }
    }

    private void signIn(DataSnapshot dataSnapshot) {
        User user = dataSnapshot.getValue(User.class);
        if (user == null) {
            signUp();
        }
        firebaseHelper.changeUserConnectionStatus(User.ONLINE);
        event(LoginEvent.SIGN_IN_SUCCESS);

    }

    private void event(int type) {
        event(type, null);
    }

    private void event(int type, String message) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setType(type);
        if (message != null) {
            loginEvent.setMessage(message);
        }
        EventBus eventBus = EventBus.getDefault();
        eventBus.post(loginEvent);
    }
}
