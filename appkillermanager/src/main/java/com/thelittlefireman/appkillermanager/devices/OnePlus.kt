package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer

class OnePlus : Device() {

    override fun isThatRom() = Build.BRAND.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.MANUFACTURER.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.FINGERPRINT.contains(deviceManufacturer.toString(), ignoreCase = true)

    // This is mandatory for new oneplus version android 8
    override fun needToUseAlongWithActionDoseMode(): Boolean = true

    override fun getDeviceManufacturer() = Manufacturer.ONEPLUS

    override fun getActionAutoStart(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity")
    }

    override fun isActionPowerSavingAvailable(context: Context) = false

    override fun isActionAutoStartAvailable(context: Context) = true

    override fun isActionNotificationAvailable(context: Context) = false

    override fun getActionPowerSaving(context: Context) = null

    override fun getActionNotification(context: Context) = null

    override fun getExtraDebugInformations(context: Context) = null
}
