package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;

import com.thelittlefireman.appkillermanager.utils.Manufacturer;

public class Meizu implements DeviceBase{
    @Override
    public boolean isThatRom() {
        return false;
    }

    @Override
    public Manufacturer getDeviceManufacturer() {
        return null;
    }

    @Override
    public Intent getActionPowerSaving(Context context) {
        +        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && Build.MODEL.equals("MX4")) {
            +            return new ComponentName("com.meizu.safe", "com.meizu.safe.powerui.PowerAppPermissionActivity");
            +        }
        +        getExtrasHolderIntent().putExtra("packageName", getContext().getPackageName());
        +        return new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        "new Intent(""com.meizu.safe.security.SHOW_APPSEC"");
        meizuIntent.addCategory(Intent.CATEGORY_DEFAULT);
        meizuIntent.putExtra(""packageName"", DaemonEnv.sApp.getPackageName());"

        return null;
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        return null;
    }

    public Intent getActionNotification(Context context){
        +        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && Build.MODEL.equals("MX4")) {
            +            return new ComponentName("com.meizu.safe", "com.meizu.safe.permission.NotificationActivity");
            +        }
        +        getExtrasHolderIntent().putExtra("packageName", getContext().getPackageName());
        +        return new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
    }
}
