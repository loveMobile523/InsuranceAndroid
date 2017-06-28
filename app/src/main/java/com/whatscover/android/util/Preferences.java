package com.whatscover.android.util;

/**
 * Created by arazfarhang on 30/04/2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;

import com.whatscover.android.utility.PreferenceUtility;

/**
 * Created by arazfarhang on 10/06/2016.
 */
public class Preferences extends PreferenceUtility{
    private static Preferences instance;

    //    private static FirebaseAnalytics analytics ;
    private Preferences(@NonNull Context context) {
        super(context);
    }

    /**
     * @param context
     * @return Returns a 'Prefs' instance
     */
    public static Preferences with(@NonNull Context context) {

//        analytics = FirebaseAnalytics.getInstance(context);
        if (instance == null) {
            instance = new Preferences(context);
        }
        return instance;

    }

}
