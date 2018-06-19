package com.thelittlefireman.appkillermanager.killerManager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hypertrack.hyperlog.HyperLog;
import com.thelittlefireman.appkillermanager.devices.DeviceBase;
import com.thelittlefireman.appkillermanager.devices.DevicesManager;
import com.thelittlefireman.appkillermanager.utils.ActionsUtils;
import com.thelittlefireman.appkillermanager.utils.LogUtils;
import com.thelittlefireman.appkillermanager.utils.SystemUtils;

public class KillerManager {

    private enum ACTIONS {
        AUTOSTART,
        NOTIFICATIONS,
        POWERSAVING
    }

    private static void init(Context context) {
        // log error into a distant request bin logs for helps to debug
        // please do no change the adress
        HyperLog.initialize(context);
        HyperLog.setLogLevel(Log.VERBOSE);
        HyperLog.setURL("API URL");
    }


    private static void doAction(Context context, ACTIONS actions) {
        // Avoid main app to crash when intent denied by using try catch
        try {
            init(context);
            DeviceBase device = DevicesManager.getDevice();
            if (device != null) {
                Intent intent = null;
                switch (actions) {
                    case AUTOSTART:
                        intent = device.getActionAutoStart(context);
                        break;
                    case POWERSAVING:
                        intent = device.getActionPowerSaving(context);
                        break;
                    case NOTIFICATIONS:
                        intent = device.getActionNotification(context);
                        break;
                }
                if (intent != null && ActionsUtils.isIntentAvailable(context, intent)) {
                    context.startActivity(intent);
                } else {
                    LogUtils.e(KillerManager.class.getName(), "INTENT NOT FOUND :" +
                            ActionsUtils.getExtrasDebugInformations(intent) + "ACTIONS \n" +
                            actions.name() + "SYSTEM UTILS \n" +
                            SystemUtils.getDefaultDebugInformation() + "DEVICE \n" +
                            device.getExtraDebugInformations(context));
                }
            } else {
               /* LogUtils.e(KillerManager.class.getName(), "DEVICE NOT FOUND" + "SYSTEM UTILS \n" +
                        SystemUtils.getDefaultDebugInformation());*/
            }
        } catch (Exception e) {
            LogUtils.e(KillerManager.class.getName(), e.getMessage());
        }
    }

    public static void doActionAutoStart(Context context) {
        doAction(context, ACTIONS.AUTOSTART);
    }

    public static void doActionNotification(Context context) {
        doAction(context, ACTIONS.NOTIFICATIONS);
    }

    public static void doActionPowerSaving(Context context) {
        doAction(context, ACTIONS.POWERSAVING);
    }
}
