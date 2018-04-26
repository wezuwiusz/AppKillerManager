package com.thelittlefireman.appkillermanager.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemUtils {
    public static String getRomName() {
        try {
            return SystemUtils.getSystemProperty("ro.build.version.emui");
        } catch (Exception e) {
            return "";
        }
    }
    public static String getApplicationName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "Unknown");
    }

    public static String getMiuiRomName() {
        try {
            return SystemUtils.getSystemProperty("ro.miui.ui.version.name");
        } catch (Exception e) {
            return "";
        }
    }

    private static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            Log.e(SystemUtils.class.getClass().getName(), "Unable to read system property " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.e(SystemUtils.class.getClass().getName(), "Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }
}
