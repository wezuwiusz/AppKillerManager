package com.thelittlefireman.appkillermanager.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.thelittlefireman.appkillermanager.utils.ActionsUtils;
import com.thelittlefireman.appkillermanager.utils.Manufacturer;

// TODO TESTS
public class Asus extends DeviceAbstract {

    private static final String ASUS_PACAKGE_MOBILEMANAGER = "com.asus.mobilemanager";
    private static final String ASUS_ACTIVITY_MOBILEMANAGER_FUNCTION_ACTIVITY = "com.asus.mobilemanager.entry.FunctionActivity";
    private static final String ASUS_ACTIVITY_MOBILEMANAGER_FUNCTION_AUTOSTART_ACTIVITY = "com.asus.mobilemanager.autostart.AutoStartActivity";

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
    public boolean isActionPowerSavingAvailable(Context context) {
        return false;
    }

    @Override
    public boolean isActionAutoStartAvailable(Context context) {
        return true;
    }

    @Override
    public boolean isActionNotificationAvailable(Context context) {
        return false;
    }

    @Override
    public Intent getActionPowerSaving(Context context) {
        // TODO JUST NEED TO USE THE REGULAR BATTERY NON OPTIMISATION PERMISSION =)
        return null;
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        Intent intent = ActionsUtils.createIntent();
        intent.putExtra("showNotice",true);
        intent.setComponent(new ComponentName(ASUS_PACAKGE_MOBILEMANAGER, ASUS_ACTIVITY_MOBILEMANAGER_FUNCTION_AUTOSTART_ACTIVITY));
        return intent;
    }

    @Override
    public Intent getActionNotification(Context context) {
        return null;
    }

    @Override
    public String getExtraDebugInformations(Context context) {
        return null;
    }

    @Override
    public int getHelpImage() {
        return 0;
    }
}
