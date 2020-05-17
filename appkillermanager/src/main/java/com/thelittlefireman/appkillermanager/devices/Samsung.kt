package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.thelittlefireman.appkillermanager.R
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer

class Samsung : Device {

    companion object {
        // crash "com.samsung.android.lool","com.samsung.android.sm.ui.battery.AppSleepListActivity"
        private const val SAMSUNG_SYSTEMMANAGER_POWERSAVING_ACTION = "com.samsung.android.sm.ACTION_BATTERY"
        private const val SAMSUNG_SYSTEMMANAGER_NOTIFICATION_ACTION = "com.samsung.android.sm.ACTION_SM_NOTIFICATION_SETTING"

        // ANDROID 7.0/8.0
        private const val SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V3 = "com.samsung.android.lool"
        private const val SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V3_ACTIVITY = "com.samsung.android.sm.ui.battery.BatteryActivity"

        // ANDROID 6.0
        private const val SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V2 = "com.samsung.android.sm_cn"
        private const val SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V2_ACTIVITY = "com.samsung.android.sm.ui.battery.BatteryActivity"

        // ANDROID 5.0/5.1
        private const val SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V1 = "com.samsung.android.sm"
        private const val SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V1_ACTIVITY = "com.samsung.android.sm.ui.battery.BatteryActivity"
        private const val SAMSUNG_SYSTEMMANAGER_AUTOSTART_PACKAGE_V1 = "com.samsung.memorymanager"
        private const val SAMSUNG_SYSTEMMANAGER_AUTOSTART_PACKAGE_V1_ACTIVITY = "com.samsung.memorymanager.RamActivity"
    }

    override val helpImagePowerSaving: Int
        get() = R.drawable.samsung

    override val isThatRom: Boolean
        get() = Build.BRAND.equals(manufacturer.toString(), ignoreCase = true) ||
                Build.MANUFACTURER.equals(manufacturer.toString(), ignoreCase = true) ||
                Build.FINGERPRINT.contains(manufacturer.toString(), ignoreCase = true)

    override val manufacturer: Manufacturer
        get() = Manufacturer.SAMSUNG

    // SmartManager is not available before lollipop version
    override fun isActionPowerSavingAvailable(context: Context) = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    override fun needToUseAlongWithActionDoseMode() = true

    override fun getActionPowerSaving(context: Context): Intent? {
        var intent = ActionsUtils.createIntent()
        intent.action = SAMSUNG_SYSTEMMANAGER_POWERSAVING_ACTION
        if (ActionsUtils.isIntentAvailable(context, intent)) {
            return intent
        }
        // reset
        intent = ActionsUtils.createIntent()
        intent.component = ComponentName(SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V3, SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V3_ACTIVITY)
        if (ActionsUtils.isIntentAvailable(context, intent)) {
            return intent
        }
        intent.component = ComponentName(SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V2, SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V2_ACTIVITY)
        if (ActionsUtils.isIntentAvailable(context, intent)) {
            return intent
        }
        intent.component = ComponentName(SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V1, SAMSUNG_SYSTEMMANAGER_POWERSAVING_PACKAGE_V1_ACTIVITY)
        return if (ActionsUtils.isIntentAvailable(context, intent)) {
            intent
        } else null
    }

    // FIXME: Currently not working - not available, ITS NOT AUTO START ITS MEMORY MANAGER
    override fun getActionAutoStart(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName(SAMSUNG_SYSTEMMANAGER_AUTOSTART_PACKAGE_V1, SAMSUNG_SYSTEMMANAGER_AUTOSTART_PACKAGE_V1_ACTIVITY)
    }

    // FIXME: NOT WORKING - NEED PERMISSIONS SETTINGS OR SOMETHINGS ELSE
    override fun getActionNotification(context: Context): Intent = ActionsUtils.createIntent().apply {
        action = SAMSUNG_SYSTEMMANAGER_NOTIFICATION_ACTION
    }
}
