package com.thelittlefireman.appkillermanager.utils;

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
