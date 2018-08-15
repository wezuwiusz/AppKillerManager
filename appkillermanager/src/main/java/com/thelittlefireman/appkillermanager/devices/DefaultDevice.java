package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

public class DefaultDevice extends DeviceAbstract {
    @Override
    public boolean isThatRom() {
        return false;
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return null;
    }

    @Override
    public boolean isActionPowerSavingAvailable(Context context) {
        return true;
    }

    @Override
    public boolean isActionAutoStartAvailable(Context context) {
        return false;
    }

    @Override
    public boolean isActionNotificationAvailable(Context context) {
        return false;
    }

    @Override
    public Intent getActionPowerSaving(Context context) {
        Intent intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        return intent;
    }
    /*            //Android 7.0+ Doze
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                PowerManager pm = (PowerManager) DaemonEnv.sApp.getSystemService(Context.POWER_SERVICE);
                boolean ignoringBatteryOptimizations = pm.isIgnoringBatteryOptimizations(DaemonEnv.sApp.getPackageName());
                if (!ignoringBatteryOptimizations) {
                    Intent dozeIntent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                    dozeIntent.setData(Uri.parse("package:" + DaemonEnv.sApp.getPackageName()));
                    sIntentWrapperList.add(new IntentWrapper(dozeIntent, DOZE));
                }
            }*/

    @Override
    public Intent getActionAutoStart(Context context) {
        return null;
    }

    @Override
    public Intent getActionNotification(Context context) {
        return null;
    }

    @Override
    public String getExtraDebugInformations(Context context) {
        return null;
    }

    @Override
    public int getHelpImage() {
        return 0;
    }
    /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
     PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
     boolean ignoringBatteryOptimizations = pm.isIgnoringBatteryOptimizations(context.getPackageName());
     if (!ignoringBatteryOptimizations) {
         Intent dozeIntent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
         dozeIntent.setData(Uri.parse("package:" + context.getPackageName()));
         startActivity(dozeIntent);
     }
 }*/
}
