package com.thelittlefireman.appkillermanager.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;
import com.thelittlefireman.appkillermanager.utils.ActionsUtils;

public class Samsung implements DeviceBase {

    // ANDROID 7.0
    private static String p1 = "com.samsung.android.lool";
    private static String p1c1 = "com.samsung.android.sm.ui.battery.BatteryActivity";

    // ANDROID 5.0/5.1
    private static String p2 ="com.samsung.android.sm";
    private static String p2c1 ="com.samsung.android.sm.ui.battery.BatteryActivity";

    // ANDROID 6.0
    private static String p21 ="com.samsung.android.sm_cn";
    private static String p21c1 ="com.samsung.android.sm.ui.battery.BatteryActivity";

    @Override
    public boolean isThatRom() {
        return Build.BRAND.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.MANUFACTURER.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.FINGERPRINT.toLowerCase().contains(getDeviceManufacturer().toString());
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.SAMSUNG;
    }

    @Override
    public Intent getActionPowerSaving(Context context) {
        Intent intent = ActionsUtils.createIntent();
        intent.setComponent(getComponentName1());
        if(ActionsUtils.isIntentAvailable(context,intent)){
            return intent;
        }
        intent.setComponent(getComponentName2());
        if (ActionsUtils.isIntentAvailable(context,intent)){
            return intent;
        }
        intent.setComponent(getComponentName21());
        if (ActionsUtils.isIntentAvailable(context,intent)){
            return intent;
        }
        return null;
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        return null;
    }

    private ComponentName getComponentName1(){
        return new ComponentName(p1,p1c1);
    }
    private ComponentName getComponentName2(){
        return new ComponentName(p2,p2c1);
    }
    private ComponentName getComponentName21(){
        return new ComponentName(p21,p21c1);
    }
}
