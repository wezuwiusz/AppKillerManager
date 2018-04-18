package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

import java.util.List;

public interface DeviceBase {
    Manufacturer getDeviceManufacturer();
    List<Intent> getAutoStartSettings(Context context);
}
