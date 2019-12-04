package com.tc.sachin.apputilities;

import android.content.Context;
import android.content.SharedPreferences;

public final class Prefs {

    public static SharedPreferences get(Context context){
        return context.getSharedPreferences(context.getPackageName() , Context.MODE_PRIVATE);
    }
}