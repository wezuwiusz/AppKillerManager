package com.thelittlefireman.appkillermanager.devices;

import android.content.ComponentName;
import android.content.Context;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

import java.util.Arrays;
import java.util.List;

public class Huawei implements DeviceBase {
    private static final String p1 = "com.huawei.systemmanager";
    private static final String c1 = "com.huawei.systemmanager.optimize.process.ProtectActivity";

    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.HUAWEI;
    }

    @Override
    public List<ComponentName> getAutoStartSettings(Context context) {
        return Arrays.asList(new ComponentName(p1,c1));
    }
}
