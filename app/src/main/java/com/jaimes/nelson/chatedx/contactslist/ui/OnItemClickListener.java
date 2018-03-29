package com.jaimes.nelson.chatedx.contactslist.ui;

import com.jaimes.nelson.chatedx.login.entities.User;

/**
 * Created by NJG_3 on 27/03/2018.
 */

public interface OnItemClickListener {
    void onClick(User user);

    void onLongClick(User user);
}
