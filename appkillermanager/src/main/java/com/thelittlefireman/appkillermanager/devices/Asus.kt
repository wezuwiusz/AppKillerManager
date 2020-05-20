package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import com.thelittlefireman.appkillermanager.R
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer
import timber.log.Timber

// TODO TESTS
class Asus : Device {

    companion object {
        private const val ASUS_PACKAGE_MOBILE_MANAGER = "com.asus.mobilemanager"
        private const val ASUS_ACTIVITY_MOBILE_MANAGER_FUNCTION_ACTIVITY = "com.asus.mobilemanager.entry.FunctionActivity"
        private const val ASUS_ACTIVITY_MOBILE_MANAGER_FUNCTION_AUTO_START_ACTIVITY = "com.asus.mobilemanager.autostart.AutoStartActivity"
    }

    override val isThatRom: Boolean
        get() = Build.BRAND.equals(manufacturer.toString(), ignoreCase = true) ||
                Build.MANUFACTURER.equals(manufacturer.toString(), ignoreCase = true) ||
                Build.FINGERPRINT.contains(manufacturer.toString(), ignoreCase = true)

    override val manufacturer: Manufacturer
        get() = Manufacturer.ASUS

    override fun isActionPowerSavingAvailable(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            return pm.isIgnoringBatteryOptimizations(context.packageName)
        }
        return false
    }

    override fun isActionAutoStartAvailable(context: Context) = true

    override fun isActionNotificationAvailable(context: Context) = true

    override fun getActionPowerSaving(context: Context): Intent? {
        // Just need to use the regular battery non optimization permission =)
        //Android 7.0+ Doze
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            if (!pm.isIgnoringBatteryOptimizations(context.packageName)) {
                // Cannot fire Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                // due to Google play device policy restriction !
                return ActionsUtils.createIntent().apply {
                    action = Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
                    data = Uri.parse("package:" + context.packageName)
                }
            } else {
                Timber.tag(this.javaClass.name).i("getActionDozeMode: App is already enable to ignore doze battery optimization")
            }
        }
        return null
    }

    override fun getActionAutoStart(context: Context): Intent = ActionsUtils.createIntent().apply {
        putExtra("showNotice", true)
        component = ComponentName(ASUS_PACKAGE_MOBILE_MANAGER, ASUS_ACTIVITY_MOBILE_MANAGER_FUNCTION_AUTO_START_ACTIVITY)
    }

    // Need to click on notifications items
    override fun getActionNotification(context: Context): Intent = ActionsUtils.createIntent().apply {
        putExtra("showNotice", true)
        component = ComponentName(ASUS_PACKAGE_MOBILE_MANAGER, ASUS_ACTIVITY_MOBILE_MANAGER_FUNCTION_ACTIVITY)
    }
}
