package com.thelittlefireman.appkillermanager.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;
import com.thelittlefireman.appkillermanager.utils.PackageUtils;

import java.util.ArrayList;
import java.util.List;

public class Samsung implements DeviceBase {

    private static String p1 = "com.samsung.android.lool";
    private static String p1c1 = "com.samsung.android.sm.ui.battery.BatteryActivity";

    private static String p2 ="com.samsung.android.sm";
    private static String p2c1 ="com.samsung.android.sm.ui.battery.BatteryActivity";

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.SAMSUNG;
    }

    @Override
    public List<Intent> getAutoStartSettings(Context context) {
        List<ComponentName> componentNames = new ArrayList<>();
        if(PackageUtils.isPackageExisted(context, p1)){
            componentNames.add(new ComponentName(p1,p1c1));
        }
        if(PackageUtils.isPackageExisted(context,p2)){
            componentNames.add(new ComponentName(p2,p2c1));
        }
        return componentNames;
    }

    private List<ComponentName> getPackages(Context context){
        List<ComponentName> componentNames = new ArrayList<>();
        if(PackageUtils.isPackageExisted(context,p1)){
            componentNames.add(new ComponentName(p1,p1c1));
        }
        if(PackageUtils.isPackageExisted(context,p2)){
            componentNames.add(new ComponentName(p2,p2c1));
        }
        return componentNames;
    }

}
