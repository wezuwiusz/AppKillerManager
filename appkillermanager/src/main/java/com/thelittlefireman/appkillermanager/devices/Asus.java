package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

public class Asus implements DeviceBase {

       /* new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.entry" +
            ".FunctionActivity")).setData(
            Uri.parse("mobilemanager://function/entry/AutoStart"))*/
    @Override
    public boolean isThatRom() {
        return false;
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return null;
    }

    @Override
    public Intent getActionPowerSaving(Context context) {
        return null;
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        return null;
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
