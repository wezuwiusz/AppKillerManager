package com.thelittlefireman.appkillermanager.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.thelittlefireman.appkillermanager.utils.ActionsUtils;
import com.thelittlefireman.appkillermanager.utils.Manufacturer;
import com.thelittlefireman.appkillermanager.utils.SystemUtils;

import java.util.Arrays;
import java.util.List;

import static com.thelittlefireman.appkillermanager.utils.SystemUtils.getRomName;

public class Huawei implements DeviceBase {

    private static final String actionIntent = "huawei.intent.action.HSM_PROTECTED_APPS";

    @Override
    public boolean isThatRom() {
        return isEmotionUI_3() ||
                isEmotionUI_3() ||
                isEmotionUI_301() ||
                isEmotionUI_31() ||
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
    public Intent getAction(Context context) {
        Intent intent = ActionsUtils.createIntent();
        intent.setAction(actionIntent);
        return intent;
    }
}
