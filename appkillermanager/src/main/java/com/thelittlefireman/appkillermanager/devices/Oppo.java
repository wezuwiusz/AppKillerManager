package com.thelittlefireman.appkillermanager.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;
import com.thelittlefireman.appkillermanager.utils.PackageUtils;

import java.util.ArrayList;
import java.util.List;

public class Oppo implements DeviceBase {
    private static final String p1 = "com.coloros.safecenter";
    private static final String p1c1 = "com.coloros.safecenter.permission.startup.StartupAppListActivity";
    private static final String p1c2 = "com.coloros.safecenter.startupapp.StartupAppListActivity";

    private static final String p2 = "com.oppo.safe";
    private static final String p2c1 = "com.oppo.safe.permission.startup.StartupAppListActivity";

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.OPPO;
    }

    @Override
    public List<Intent> getAutoStartSettings(Context context) {
        List<Intent> intents = new ArrayList<>();
        for (ComponentName componentName : getPackages(context)) {
            Intent intent = new Intent();
            intent.setComponent(componentName);
        }
        return intents;
    }

    private List<ComponentName> getPackages(Context context){
        List<ComponentName> componentNames = new ArrayList<>();
        if(PackageUtils.isPackageExisted(context,p1)){
            componentNames.add(new ComponentName(p1,p1c1));
            componentNames.add(new ComponentName(p1,p1c2));
        }
        if(PackageUtils.isPackageExisted(context,p2)){
            componentNames.add(new ComponentName(p2,p2c1));
        }
        return componentNames;
    }
}
