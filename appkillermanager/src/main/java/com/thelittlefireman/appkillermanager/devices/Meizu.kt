package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer
import timber.log.Timber

class Meizu : Device() {

    companion object {
        private const val MEIZU_DEFAULT_ACTION_APPSPEC = "com.meizu.safe.security.SHOW_APPSEC"
        private const val MEIZU_DEFAULT_EXTRA_PACKAGE = "packageName"
        private const val MEIZU_DEFAULT_PACKAGE = "com.meizu.safe"

        private const val MEIZU_NOTIFICATION_ACTIVITY = "com.meizu.safe.permission.NotificationActivity"

        private const val MEIZU_POWER_SAVING_ACTION = "com.meizu.power.PowerAppKilledNotification"
        private const val MEIZU_POWER_SAVING_ACTIVITY_V2_2 = "com.meizu.safe.cleaner.RubbishCleanMainActivity"
        private const val MEIZU_POWER_SAVING_ACTIVITY_V3_4 = "com.meizu.safe.powerui.AppPowerManagerActivity"
        private const val MEIZU_POWER_SAVING_ACTIVITY_V3_7 = "com.meizu.safe.powerui.PowerAppPermissionActivity" // == ACTION com.meizu.power.PowerAppKilledNotification
    }

    private enum class MEIZU_SECURITY_CENTER_VERSION {
        SEC_2_2,  //Meizu security center : 2.2.0922, 2.2.0310;
        SEC_3_4,  //Meizu security center : 3.4.0316;
        SEC_3_6,  //Meizu security center : 3.6.0802;
        SEC_3_7,  //Meizu security center : 3.7.1101;
        SEC_4_1
        //Meizu security center : 4.1.10;
    }

    override fun isThatRom() = Build.BRAND.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.MANUFACTURER.equals(deviceManufacturer.toString(), ignoreCase = true) ||
            Build.FINGERPRINT.contains(deviceManufacturer.toString(), ignoreCase = true)

    override fun getDeviceManufacturer() = Manufacturer.MEIZU

    override fun isActionPowerSavingAvailable(context: Context) = true

    override fun isActionAutoStartAvailable(context: Context) = true

    override fun isActionNotificationAvailable(context: Context) = true

    override fun getActionPowerSaving(context: Context): Intent {
        val intent = ActionsUtils.createIntent().apply {
            action = MEIZU_POWER_SAVING_ACTION
        }
        if (ActionsUtils.isIntentAvailable(context, intent)) return intent

        val intent2 = ActionsUtils.createIntent()
        when (getMeizuSecVersion(context)) {
            MEIZU_SECURITY_CENTER_VERSION.SEC_2_2 -> intent2.setClassName(MEIZU_DEFAULT_PACKAGE, MEIZU_POWER_SAVING_ACTIVITY_V2_2)
            MEIZU_SECURITY_CENTER_VERSION.SEC_3_4 -> intent2.setClassName(MEIZU_DEFAULT_PACKAGE, MEIZU_POWER_SAVING_ACTIVITY_V3_4)
            MEIZU_SECURITY_CENTER_VERSION.SEC_3_7 -> intent2.setClassName(MEIZU_DEFAULT_PACKAGE, MEIZU_POWER_SAVING_ACTIVITY_V3_7)
            else -> return getDefaultSettingAction(context)
        }
        return intent2
    }

    override fun getActionAutoStart(context: Context): Intent = getDefaultSettingAction(context)

    private fun getDefaultSettingAction(context: Context) = ActionsUtils.createIntent().apply {
        action = MEIZU_DEFAULT_ACTION_APPSPEC
        putExtra(MEIZU_DEFAULT_EXTRA_PACKAGE, context.packageName)
    }

    override fun getActionNotification(context: Context): Intent {
        val mSecVersion = getMeizuSecVersion(context)
        val intent = ActionsUtils.createIntent()
        return if (mSecVersion == MEIZU_SECURITY_CENTER_VERSION.SEC_3_7 || mSecVersion == MEIZU_SECURITY_CENTER_VERSION.SEC_4_1) {
            intent.component = ComponentName(MEIZU_DEFAULT_PACKAGE, MEIZU_NOTIFICATION_ACTIVITY)
            intent
        } else {
            getDefaultSettingAction(context)
        }
    }

    override fun getExtraDebugInformations(context: Context): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("MeizuSecVersionMethod:").append(getMeizuSecVersion(context))

        var versionStr = ""
        try {
            versionStr = context.packageManager.getPackageInfo(MEIZU_DEFAULT_PACKAGE, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.e(e)
        }
        stringBuilder.append("MeizuSecPackageVersion:").append(versionStr)

        // ----- PACAKGE INFORMATIONS -----
        stringBuilder.append(MEIZU_DEFAULT_ACTION_APPSPEC).append(ActionsUtils.isIntentAvailable(context, MEIZU_DEFAULT_ACTION_APPSPEC))
        stringBuilder.append(MEIZU_POWER_SAVING_ACTION).append(ActionsUtils.isIntentAvailable(context, MEIZU_POWER_SAVING_ACTION))
        stringBuilder.append(MEIZU_DEFAULT_PACKAGE + MEIZU_POWER_SAVING_ACTIVITY_V2_2).append(ActionsUtils.isIntentAvailable(context, ComponentName(MEIZU_DEFAULT_PACKAGE, MEIZU_POWER_SAVING_ACTIVITY_V2_2)))
        stringBuilder.append(MEIZU_DEFAULT_PACKAGE + MEIZU_POWER_SAVING_ACTIVITY_V3_4).append(ActionsUtils.isIntentAvailable(context, ComponentName(MEIZU_DEFAULT_PACKAGE, MEIZU_POWER_SAVING_ACTIVITY_V3_4)))
        stringBuilder.append(MEIZU_DEFAULT_PACKAGE + MEIZU_POWER_SAVING_ACTIVITY_V3_7).append(ActionsUtils.isIntentAvailable(context, ComponentName(MEIZU_DEFAULT_PACKAGE, MEIZU_POWER_SAVING_ACTIVITY_V3_7)))
        stringBuilder.append(MEIZU_DEFAULT_PACKAGE + MEIZU_NOTIFICATION_ACTIVITY).append(ActionsUtils.isIntentAvailable(context, MEIZU_POWER_SAVING_ACTION))

        return stringBuilder.toString()
    }

    private fun getMeizuSecVersion(context: Context): MEIZU_SECURITY_CENTER_VERSION {
        return try {
            val info = context.packageManager.getPackageInfo(MEIZU_DEFAULT_PACKAGE, 0)
            val versionStr = info.versionName //2.2.0922;
            Timber.i(versionStr)
            when {
                versionStr.startsWith("2") -> MEIZU_SECURITY_CENTER_VERSION.SEC_2_2
                versionStr.startsWith("3") -> {
                    val d = versionStr.substring(2, 3).toInt()
                    Timber.i("d: %s", d)
                    when {
                        d <= 4 -> MEIZU_SECURITY_CENTER_VERSION.SEC_3_4
                        d < 7 -> MEIZU_SECURITY_CENTER_VERSION.SEC_3_6
                        else -> MEIZU_SECURITY_CENTER_VERSION.SEC_3_7
                    }
                }
                versionStr.startsWith("4") -> MEIZU_SECURITY_CENTER_VERSION.SEC_4_1
                else -> MEIZU_SECURITY_CENTER_VERSION.SEC_4_1
            }
        } catch (e: Exception) {
            MEIZU_SECURITY_CENTER_VERSION.SEC_4_1
        }
    }
}
