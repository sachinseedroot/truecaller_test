package com.tc.sachin.apputilities;

import android.app.AlertDialog;
import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class AppUtilities {

    private static String classname = AppUtilities.class.getSimpleName();

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
