package com.thelittlefireman.appkillermanager.killerManager;

import android.content.Context;
import android.util.Log;

import com.thelittlefireman.appkillermanager.devices.DeviceBase;
import com.thelittlefireman.appkillermanager.devices.DevicesManager;
import com.thelittlefireman.appkillermanager.utils.ActionsUtils;

public class NotificationSettingsManager {

    public static void doAction(Context context){
        DeviceBase device = DevicesManager.getDevice();
        if(device !=null){
           if(ActionsUtils.isIntentAvailable(context,device.getActionPowerSaving(context))){
                context.startActivity(device.getActionPowerSaving(context));
            }else {
               Log.i(NotificationSettingsManager.class.getName(),"INTENT NOT FOUND");
           }
        }else {
            Log.i(NotificationSettingsManager.class.getName(),"DEVICE NOT FOUND");
        }


    }
}
