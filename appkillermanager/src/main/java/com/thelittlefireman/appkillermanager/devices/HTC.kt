package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer

class HTC : Device {

    companion object {
        private const val HTC_PITROAD_PACKAGE_NAME = "com.htc.pitroad"
        private const val HTC_PITROAD_POWERSAVING = " com.htc.pitroad.landingpage.activity.LandingPageActivity"
    }

    override val isThatRom: Boolean
        get() = Build.BRAND.equals(deviceManufacturer.toString(), ignoreCase = true) ||
                Build.MANUFACTURER.equals(deviceManufacturer.toString(), ignoreCase = true) ||
                Build.FINGERPRINT.contains(deviceManufacturer.toString(), ignoreCase = true)

    override val deviceManufacturer: Manufacturer
        get() = Manufacturer.HTC

    override fun getActionPowerSaving(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName(HTC_PITROAD_PACKAGE_NAME, HTC_PITROAD_POWERSAVING)
    }

    override fun isActionPowerSavingAvailable(context: Context) = true
}
