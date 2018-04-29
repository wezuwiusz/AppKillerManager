package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

public class Oppo implements DeviceBase {
    //coloros3.0
    private static final String p1 = "com.coloros.safecenter";
    private static final String p1c1 = "com.coloros.safecenter.permission.startup.StartupAppListActivity";
    private static final String p1c2 = "com.coloros.safecenter.startupapp.StartupAppListActivity";

    private static final String p12 = "com.coloros.oppoguardelf";
    private static final String p12c1 = "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity";

    //OLD == ColorOS V2.1
    private static final String p13 = "com.color.oppoguardelf";
    private static final String p13c1 = "com.color.safecenter.permission.startup.StartupAppListActivity";
    private static final String p13c2 = "com.color.safecenter.startupapp.StartupAppListActivity";

    private static final String p2 = "com.oppo.safe";
    private static final String p2c1 = "com.oppo.safe.permission.startup.StartupAppListActivity";

    @Override
    public boolean isThatRom() {
        return Build.BRAND.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.MANUFACTURER.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.FINGERPRINT.toLowerCase().contains(getDeviceManufacturer().toString());
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.OPPO;
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
/*
    private ComponentName getComponentName(Context context){
        if(ActionsUtils.isPackageExist(context,p1)){

        }
        else if(ActionsUtils.isPackageExist(context,p12)){

        }
        else if(ActionsUtils.isPackageExist(context,p13)){

        }
        else if(ActionsUtils.isPackageExist(context,p2)){

        }
        return null;
    }*/
}
