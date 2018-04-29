package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

public interface DeviceBase {
    boolean isThatRom();
    Manufacturer getDeviceManufacturer();
    Intent getActionPowerSaving(Context context);
    Intent getActionAutoStart(Context context);
    Intent getActionNotification(Context context);
    String getExtraDebugInformations(Context context);
}
