package com.thelittlefireman.appkillermanager.utils;

import android.content.Context;
import android.content.pm.PackageManager;

public class PackageUtils {
    public static boolean isPackageExisted(Context context,String targetPackage){
        PackageManager pm=context.getPackageManager();
        try {
            pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }
}
