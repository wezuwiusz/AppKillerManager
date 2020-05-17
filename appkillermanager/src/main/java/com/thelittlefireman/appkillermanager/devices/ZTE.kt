package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer

class ZTE : Device() {

    companion object {
        private const val ZTE_HEARTYSERVICE_PACKAGE_NAME = "com.zte.heartyservice"
        private const val ZTE_HEARTYSERVICE_AUTOSTART_ACTIVITY = "com.zte.heartyservice.autorun.AppAutoRunManager"
        private const val ZTE_HEARTYSERVICE_POWERSAVING_ACTIVITY = "com.zte.heartyservice.setting.ClearAppSettingsActivity"
    }

    override fun isThatRom() = Build.BRAND.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.MANUFACTURER.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.FINGERPRINT.contains(deviceManufacturer.toString(), ignoreCase = true)

    override fun getDeviceManufacturer() = Manufacturer.ZTE

    override fun isActionPowerSavingAvailable(context: Context) = true

    override fun isActionAutoStartAvailable(context: Context) = true

    override fun isActionNotificationAvailable(context: Context) = false

    override fun getActionPowerSaving(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName(ZTE_HEARTYSERVICE_PACKAGE_NAME, ZTE_HEARTYSERVICE_POWERSAVING_ACTIVITY)
    }

    override fun getActionAutoStart(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName(ZTE_HEARTYSERVICE_PACKAGE_NAME, ZTE_HEARTYSERVICE_AUTOSTART_ACTIVITY)
    }

    override fun getActionNotification(context: Context) = null

    // TODO
    override fun getExtraDebugInformations(context: Context) = null
}