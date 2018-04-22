package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

import java.util.ArrayList;
import java.util.List;

public class Xiaomi implements DeviceBase {

    private static final String MIUI_ACTION = "miui.intent.action.APP_PERM_EDITOR";
    private static final String MIUI_EXTRA = "extra_pkgname";

    @Override
    public boolean isThatRom() {
        return false;
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.XIAOMI;
    }

    @Override
    public Intent getAction(Context context) {
        return null;
    }
/*
    // TODO CHECK IF GETPACKAGENAME IS NAME OF LIB OR APP
    @Override
    public List<Intent> getAutoStartSettings(Context context) {
        List<Intent> intents = new ArrayList<>();
        intents.add(new Intent("miui.intent.action.POWER_HIDE_MODE_APP_LIST").addCategory(Intent.CATEGORY_DEFAULT));
        //com.miui.powerkeeper/com.miui.powerkeeper.ui.HiddenAppsContainerManagementActivity
        intents.add(new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT));
        //com.miui.securitycenter/com.miui.permcenter.autostart.AutoStartManagementActivity
        return intents;
    }
*/
    /*
    * new Intent("miui.intent.action.POWER_HIDE_MODE_APP_LIST").addCategory(Intent.CATEGORY_DEFAULT)
new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT)
    * */

    /*
    private static int getMiuiVersion() {
        String version = SystemUtils.getSystemProperty("ro.miui.ui.version.name");
        if (version != null) {
            try {
                return Integer.parseInt(version.substring(1));
            } catch (Exception e) {
                Log.e(Xiaomi.class.getName(), "get miui version code error, version : " + version);
                Log.e(Xiaomi.class.getName(), Log.getStackTraceString(e));
            }
        }
        return -1;
    }

    public Intent miui_V5(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package" , packageName, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    public Intent miui_V6(Context context) {
        Intent intent = new Intent(MIUI_ACTION);
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra(MIUI_EXTRA, context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    // MIUI V7

    private Intent miui_V7(Context context) {
        Intent intent = new Intent(MIUI_ACTION);
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra(MIUI_EXTRA, context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    //MIUI V8

    private Intent miui_V8(Context context) {
        Intent intent = new Intent(MIUI_ACTION);
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra(MIUI_EXTRA, context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
*/
}
