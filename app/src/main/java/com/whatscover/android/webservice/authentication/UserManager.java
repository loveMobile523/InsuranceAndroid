package com.whatscover.android.webservice.authentication;

import android.content.Context;

import com.whatscover.android.webservice.dto.UserTest;
import com.whatscover.android.util.Preferences;

/**
 * Created by arazfarhang on 30/04/2017.
 */

public class UserManager {
    private static final java.lang.String PREFS_LOGIN_IS = "prefs_login_is";
    private static final String PREFS_LOGIN_USER = "prefs_login_user";
    private static final String PREFS_LOGIN_IMAGE = "prefs_login_image";
    private static final String PREFS_LOGIN_PASSWORD = "prefs_login_password";
    private static final String PREFS_LOGIN_TOKEN = "prefs_login_token";
    private static final String PREFS_LOGIN_USER_ID = "prefs_login_user_id";
    Context context;
    Preferences prefs;
    private static Object LOCK = new Object();
    private static volatile UserManager instance = null;
    private UserTest currentUser;
    private AccessToken accessToken;
    private Long melons;


    public static UserManager getInstance(Context applicationContext) {
        if (instance == null)
            synchronized (LOCK) {
                instance = new UserManager(applicationContext);
            }
        return instance;
    }

    private UserManager(Context mContext) {
        context = mContext;
        prefs = Preferences.with(context);
        currentUser = new UserTest();
    }

    public UserTest getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        // prefs.clear();
    }


    public Context getAppContext() {
        return this.context.getApplicationContext();
    }

    public void setCurrentUser(UserTest currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isLoggedIn() {
        return prefs.readBoolean(PREFS_LOGIN_IS);
    }


    public void login(UserTest currentUser) {
        this.currentUser = currentUser;
        prefs.write(PREFS_LOGIN_USER, currentUser.getName());
        prefs.write(PREFS_LOGIN_PASSWORD, currentUser.getPassword());
        prefs.write(PREFS_LOGIN_TOKEN, currentUser.getToken());
        prefs.writeBoolean(PREFS_LOGIN_IS, true);
//        prefs.write(PREFS_LOGIN_USER_ID, currentUser.getId());
    }

    public void setUserId(String userId) {
        prefs.write(PREFS_LOGIN_USER_ID, currentUser.getId());
    }

    public String getUserId() {
        return prefs.read(PREFS_LOGIN_USER_ID);
    }


    public UserTest read() {
        UserTest user = new UserTest();
        user.setName(prefs.read(PREFS_LOGIN_USER));
        user.setPassword(prefs.read(PREFS_LOGIN_PASSWORD));
        user.setToken(prefs.read(PREFS_LOGIN_TOKEN));
//        user.setId(prefs.read(PREFS_LOGIN_USER_ID));
        return user;
    }


    public String getUserName() {
        return prefs.read(PREFS_LOGIN_USER);
    }


    public String getAvatar() {
        return prefs.read(PREFS_LOGIN_IMAGE);
    }

    public void setAvatar(String image) {
        prefs.write(PREFS_LOGIN_IMAGE, image);
    }


}
