package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

public class Vivo implements DeviceBase {

    private final String p1 = "com.iqoo.secure";
    private final String p1c1 = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity";
    private final String p1c2 = "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager";

    private final String p2 = "com.vivo.permissionmanager";
    private final String p2c1 = "com.vivo.permissionmanager.activity.BgStartUpManagerActivity";

    @Override
    public boolean isThatRom() {
        return false;
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.VIVO;
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
    @Override
    public List<ComponentName> getAutoStartSettings(Context context) {
        List<ComponentName> componentNames = new ArrayList<>();
        if(ActionsUtils.isPackageExist(context, p1)){
            componentNames.add(new ComponentName(p1,p1c1));
            componentNames.add(new ComponentName(p1,p1c2));
        }
        if(ActionsUtils.isPackageExist(context,p2)){
            componentNames.add(new ComponentName(p2,p2c1));
        }
        return componentNames;
    }*/
}
