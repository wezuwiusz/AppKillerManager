package com.thelittlefireman.appkillermanager.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.thelittlefireman.appkillermanager.utils.ActionsUtils;
import com.thelittlefireman.appkillermanager.utils.Manufacturer;

public class Asus implements DeviceBase {

    private static final String ASUS_PACAKGE_MOBILEMANAGER = "com.asus.mobilemanager";
    private static final String ASUS_ACTIVITY_MOBILEMANAGER_FUNCTION_ACTIVITY = "com.asus.mobilemanager.entry.FunctionActivity";

    @Override
    public boolean isThatRom() {
        return  Build.BRAND.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.MANUFACTURER.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.FINGERPRINT.toLowerCase().contains(getDeviceManufacturer().toString());
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.ASUS;
    }

    @Override
    public Intent getActionPowerSaving(Context context) {
        return null;
        // TODO tests new ComponentName("com.asus.powersaver", "com.asus.powersaver.PowerSaverSettings")
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        //TODO TEST ENABLE NOTICE "showNotice"
        //         intent.putExtra("showNotice",true);
        Intent intent = ActionsUtils.createIntent();
        intent.setComponent(new ComponentName(ASUS_PACAKGE_MOBILEMANAGER, ASUS_ACTIVITY_MOBILEMANAGER_FUNCTION_ACTIVITY))
              .setData(Uri.parse("mobilemanager://function/entry/AutoStart"));
        return intent;
        // TODO IF NOT WORKING TRY OPEN com.asus.mobilemanager.autostart.AutoStartActivity
    }

    @Override
    public Intent getActionNotification(Context context) {
        return null;
    }

    @Override
    public String getExtraDebugInformations(Context context) {
        return null;
    }
}
