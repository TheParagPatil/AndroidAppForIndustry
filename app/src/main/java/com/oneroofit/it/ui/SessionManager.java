package com.oneroofit.it.ui;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences usersSessions;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "IsLoggedIn";

    //public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    //public static final String KEY_PHONE = "phone";

    public SessionManager(Context _context) {
        context = _context;
        usersSessions = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = usersSessions.edit();
    }

    public void createLoginSession(String fullname, String email, String password, String phone) {

        editor.putBoolean(IS_LOGIN, true);
        //editor.putString(KEY_FULLNAME, fullname);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        //editor.putString(KEY_PHONE, phone);

        editor.commit();
    }

    public HashMap<String, String> getUsersDetailsFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();

        //userData.put(KEY_FULLNAME, usersSessions.getString(KEY_FULLNAME, null));
        userData.put(KEY_EMAIL, usersSessions.getString(KEY_EMAIL, null));
        userData.put(KEY_PASSWORD, usersSessions.getString(KEY_PASSWORD, null));
        //userData.put(KEY_PHONE, usersSessions.getString(KEY_PHONE, null));
        return userData;
    }

    public boolean checkLogin() {
        if (usersSessions.getBoolean(IS_LOGIN, false)) {
            return true;
        } else {
            return false;
        }
    }

    public void logoutUserFromSession() {
        editor.clear();
        editor.commit();
    }
}
