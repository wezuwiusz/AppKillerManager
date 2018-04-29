package com.thelittlefireman.appkillermanager.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

public class LogUtils {

    public static void i(String tag, String message){
        Log.i(tag,message);
        Crashlytics.log(message);
    }
}
