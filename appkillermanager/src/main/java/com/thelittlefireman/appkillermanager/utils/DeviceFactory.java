package com.thelittlefireman.appkillermanager.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.thelittlefireman.appkillermanager.devices.DeviceBase;
import com.thelittlefireman.appkillermanager.devices.Oppo;
import com.thelittlefireman.appkillermanager.devices.Samsung;
import com.thelittlefireman.appkillermanager.devices.Vivo;
import com.thelittlefireman.appkillermanager.devices.Xiaomi;

import java.util.List;

public class DeviceFactory {

    public DeviceBase getDevice() {
        String manufacturer = android.os.Build.MANUFACTURER;
        // TODO IGNORE CASE
        switch (manufacturer) {
            case Manufacturer.XIAOMI:
                return new Xiaomi();
            break;
            case Manufacturer.OPPO:
                return new Oppo();
            break;
            case Manufacturer.VIVO:
                return new Vivo();
            break;
            case Manufacturer.SAMSUNG:
                return new Samsung();
            break;
            default:
                return null;
        }

    }
}
