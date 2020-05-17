package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer

class HTC : Device() {

    companion object {
        private const val HTC_PITROAD_PACKAGE_NAME = "com.htc.pitroad"
        private const val HTC_PITROAD_POWERSAVING = " com.htc.pitroad.landingpage.activity.LandingPageActivity"
    }

    override fun isThatRom() = Build.BRAND.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.MANUFACTURER.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.FINGERPRINT.contains(deviceManufacturer.toString(), ignoreCase = true)

    override fun getDeviceManufacturer() = Manufacturer.HTC

    override fun getActionPowerSaving(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName(HTC_PITROAD_PACKAGE_NAME, HTC_PITROAD_POWERSAVING)
    }

    override fun isActionPowerSavingAvailable(context: Context) = true

    override fun isActionAutoStartAvailable(context: Context) = false

    override fun isActionNotificationAvailable(context: Context) = false

    override fun getActionAutoStart(context: Context) = null

    override fun getActionNotification(context: Context) = null

    // TODO
    override fun getExtraDebugInformations(context: Context) = null

    override fun getHelpImagePowerSaving() = 0

    override fun needToUseAlongWithActionDoseMode() = false
}
