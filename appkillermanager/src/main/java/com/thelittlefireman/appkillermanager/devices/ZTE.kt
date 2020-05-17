package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer

class ZTE : Device {

    companion object {
        private const val ZTE_HEARTYSERVICE_PACKAGE_NAME = "com.zte.heartyservice"
        private const val ZTE_HEARTYSERVICE_AUTOSTART_ACTIVITY = "com.zte.heartyservice.autorun.AppAutoRunManager"
        private const val ZTE_HEARTYSERVICE_POWERSAVING_ACTIVITY = "com.zte.heartyservice.setting.ClearAppSettingsActivity"
    }

    override val isThatRom: Boolean
        get() = Build.BRAND.equals(manufacturer.toString(), ignoreCase = true) ||
                Build.MANUFACTURER.equals(manufacturer.toString(), ignoreCase = true) ||
                Build.FINGERPRINT.contains(manufacturer.toString(), ignoreCase = true)


    override val manufacturer: Manufacturer
        get() = Manufacturer.ZTE

    override fun isActionPowerSavingAvailable(context: Context) = true

    override fun isActionAutoStartAvailable(context: Context) = true

    override fun getActionPowerSaving(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName(ZTE_HEARTYSERVICE_PACKAGE_NAME, ZTE_HEARTYSERVICE_POWERSAVING_ACTIVITY)
    }

    override fun getActionAutoStart(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName(ZTE_HEARTYSERVICE_PACKAGE_NAME, ZTE_HEARTYSERVICE_AUTOSTART_ACTIVITY)
    }
}
