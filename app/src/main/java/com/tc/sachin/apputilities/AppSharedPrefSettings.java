package com.tc.sachin.apputilities;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPrefSettings {

    public static void setUserDetails(Context context, String RawResponse) {
        SharedPreferences prefs = Prefs.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("UserDetails", RawResponse);
        editor.commit();
    }

    public static String getUserDetails(Context context) {
        SharedPreferences prefs = Prefs.get(context);
        return prefs.getString("UserDetails", "");
    }
}
