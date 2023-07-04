package com.example.managementdormitory;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.managementdormitory.Model.User;
import com.google.gson.Gson;

public class SessionManagement {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context mContext;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "LoginSession";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_EXPIRES_AT = "expiresAt";
    private static final String KEY_USER = "user";

    public SessionManagement(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(long expireTimeMillis, User user) {
        // Convert user object to JSON string
        Gson gson = new Gson();
        String userJson = gson.toJson(user);

        // Set the expire time for the session
        editor.putLong(KEY_EXPIRES_AT, expireTimeMillis);
        // Storing login value as TRUE
        editor.putBoolean(KEY_IS_LOGGEDIN, true);
        // Storing user JSON string
        editor.putString(KEY_USER, userJson);
        // commit changes
        editor.apply();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }


    public User getUser(){
        String userJson = pref.getString(KEY_USER, "");
        Gson gson = new Gson();
        User user = gson.fromJson(userJson, User.class);
        return user;
    }

}