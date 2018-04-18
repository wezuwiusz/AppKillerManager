package com.thelittlefireman.appkillermanager.devices;

import android.content.ComponentName;
import android.content.Context;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;
import com.thelittlefireman.appkillermanager.utils.PackageUtils;

import java.util.ArrayList;
import java.util.List;

public class Vivo implements DeviceBase {

    private final String p1 = "com.iqoo.secure";
    private final String p1c1 = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity";
    private final String p1c2 = "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager";

    private final String p2 = "com.vivo.permissionmanager";
    private final String p2c1 = "com.vivo.permissionmanager.activity.BgStartUpManagerActivity";

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.VIVO;
    }

    @Override
    public List<ComponentName> getAutoStartSettings(Context context) {
        List<ComponentName> componentNames = new ArrayList<>();
        if(PackageUtils.isPackageExisted(context, p1)){
            componentNames.add(new ComponentName(p1,p1c1));
            componentNames.add(new ComponentName(p1,p1c2));
        }
        if(PackageUtils.isPackageExisted(context,p2)){
            componentNames.add(new ComponentName(p2,p2c1));
        }
        return componentNames;
    }
}
