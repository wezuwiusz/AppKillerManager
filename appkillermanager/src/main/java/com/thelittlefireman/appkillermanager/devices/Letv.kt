package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer

class Letv : Device() {

    override fun isThatRom() = Build.BRAND.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.MANUFACTURER.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.FINGERPRINT.contains(deviceManufacturer.toString(), ignoreCase = true)

    override fun getDeviceManufacturer() = Manufacturer.LETV

    override fun getActionPowerSaving(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.BackgroundAppManageActivity")
    }

    override fun getActionAutoStart(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")
    }

    override fun isActionPowerSavingAvailable(context: Context) = true

    override fun isActionAutoStartAvailable(context: Context) = true

    override fun isActionNotificationAvailable(context: Context) = false

    override fun getActionNotification(context: Context) = null

    override fun getExtraDebugInformations(context: Context) = null

    override fun getHelpImagePowerSaving() = 0
}
