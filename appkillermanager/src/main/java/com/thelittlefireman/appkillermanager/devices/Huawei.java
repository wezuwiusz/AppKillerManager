package com.thelittlefireman.appkillermanager.devices;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.thelittlefireman.appkillermanager.utils.ActionsUtils;
import com.thelittlefireman.appkillermanager.utils.Manufacturer;

import static com.thelittlefireman.appkillermanager.utils.SystemUtils.getRomName;

public class Huawei implements DeviceBase {

    // TODO NOT SUR IT WORKS ON EMUI 5
    private static final String actionIntent = "huawei.intent.action.HSM_PROTECTED_APPS";

    @Override
    public boolean isThatRom() {
        return isEmotionUI_23() ||
                isEmotionUI_3() ||
                isEmotionUI_3() ||
                isEmotionUI_301() ||
                isEmotionUI_31() ||
                isEmotionUI_41() ||
                Build.BRAND.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.MANUFACTURER.equalsIgnoreCase(getDeviceManufacturer().toString()) ||
                Build.FINGERPRINT.toLowerCase().contains(getDeviceManufacturer().toString());
    }
    public static boolean isEmotionUI_301() {
        return "EmotionUI_3.0.1".equalsIgnoreCase(getRomName());
}
    public static boolean isEmotionUI_31() {
        return "EmotionUI_3.1".equalsIgnoreCase(getRomName());
    }
    public static boolean isEmotionUI_41() {
        return "EmotionUI_4.1".equalsIgnoreCase(getRomName());
    }

    public static boolean isEmotionUI_3() {
        return "EmotionUI_3.0".equalsIgnoreCase(getRomName());
    }

    public static boolean isEmotionUI_23() {
        return "EmotionUI_2.3".equalsIgnoreCase(getRomName()) || Build.DISPLAY.toLowerCase().contains("emui2.3") || "EMUI 2.3".equalsIgnoreCase(getRomName());
    }


    @Override
    public Manufacturer getDeviceManufacturer() {
        return Manufacturer.HUAWEI;
    }

    @Override
    public Intent getActionPowerSaving(Context context) {
        Intent intent = ActionsUtils.createIntent();
        intent.setAction(actionIntent);
        return intent;
    }

    @Override
    public Intent getActionAutoStart(Context context) {
        return null;
    }
}
