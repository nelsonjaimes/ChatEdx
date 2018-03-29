package com.jaimes.nelson.chatedx.contactslist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jaimes.nelson.chatedx.R;
import com.jaimes.nelson.chatedx.contactslist.ContactsListPresenter;
import com.jaimes.nelson.chatedx.contactslist.ContactsListPresenterImpl;
import com.jaimes.nelson.chatedx.login.entities.User;

public class ContactsListActivity extends AppCompatActivity
        implements ContactsListView {

    private ContactsListPresenter contactsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        contactsListPresenter = new ContactsListPresenterImpl(this);
        contactsListPresenter.onCreate();

    }

    @Override
    protected void onResume() {
        super.onResume();
        contactsListPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        contactsListPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        contactsListPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onContactAdd(User user) {

    }

    @Override
    public void onContactRemove(User user) {

    }

    @Override
    public void onContactChange(User user) {

    }
}
