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

    public enum Actions {
        ACTION_AUTOSTART("ACTION_AUTOSTART"),
        ACTION_NOTIFICATIONS("ACTION_NOTIFICATIONS"),
        ACTION_POWERSAVING("ACTION_POWERSAVING");

        private String mValue;
        Actions(String value){
            this.mValue = value;
        }

        public String toString(){
            return this.mValue;
        }
    }

    private static DeviceBase sDevice;

    public static DeviceBase getDevice() {
        return sDevice;
    }

    private static void init(Context context) {
        // log error into a distant request bin logs for helps to debug
        // please do no change the adress
        HyperLog.initialize(context);
        HyperLog.setLogLevel(Log.VERBOSE);
        HyperLog.setURL("API URL");
        sDevice = DevicesManager.getDevice();
    }


    public static void doAction(Context context, Actions actions) {
        // Avoid main app to crash when intent denied by using try catch
        try {
            init(context);
            sDevice = DevicesManager.getDevice();
            if (sDevice != null) {
                Intent intent = null;
                switch (actions) {
                    case ACTION_AUTOSTART:
                        intent = sDevice.getActionAutoStart(context);
                        break;
                    case ACTION_POWERSAVING:
                        intent = sDevice.getActionPowerSaving(context);
                        break;
                    case ACTION_NOTIFICATIONS:
                        intent = sDevice.getActionNotification(context);
                        break;
                }
                if (intent != null && ActionsUtils.isIntentAvailable(context, intent)) {
                    context.startActivity(intent);
                } else {
                    LogUtils.e(KillerManager.class.getName(), "INTENT NOT FOUND :" +
                            ActionsUtils.getExtrasDebugInformations(intent) + "Actions \n" +
                            actions.name() + "SYSTEM UTILS \n" +
                            SystemUtils.getDefaultDebugInformation() + "DEVICE \n" +
                            sDevice.getExtraDebugInformations(context));
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
        doAction(context, Actions.ACTION_AUTOSTART);
    }

    public static void doActionNotification(Context context) {
        doAction(context, Actions.ACTION_NOTIFICATIONS);
    }

    public static void doActionPowerSaving(Context context) {
        doAction(context, Actions.ACTION_POWERSAVING);
    }
}
