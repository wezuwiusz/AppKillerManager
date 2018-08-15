package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

public interface DeviceBase {
    boolean isThatRom();
    Manufacturer getDeviceManufacturer();
    boolean isActionPowerSavingAvailable(Context context);
    boolean isActionAutoStartAvailable(Context context);
    boolean isActionNotificationAvailable(Context context);
    Intent getActionPowerSaving(Context context);
    Intent getActionAutoStart(Context context);
    // FIXME IS IT REALY NEEDED ? ==> REPLACE BY OTHER FUNCTION ?
    Intent getActionNotification(Context context);
    String getExtraDebugInformations(Context context);
    @DrawableRes int getHelpImage();
}
