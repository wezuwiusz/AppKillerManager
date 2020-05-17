package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import com.thelittlefireman.appkillermanager.R
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer
import com.thelittlefireman.appkillermanager.utils.SystemUtils.getEmuiRomName
import timber.log.Timber

class Huawei : Device {

    companion object {
        // TODO NOT SUR IT WORKS ON EMUI 5
        private const val HUAWEI_ACTION_POWER_SAVING = "huawei.intent.action.HSM_PROTECTED_APPS"
        private const val HUAWEI_ACTION_AUTO_START = "huawei.intent.action.HSM_BOOTAPP_MANAGER"
        private const val HUAWEI_ACTION_NOTIFICATION = "huawei.intent.action.NOTIFICATIONMANAGER"

        private const val HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME = "com.huawei.systemmanager"
        private const val HUAWEI_SYSTEM_MANAGER_AUTO_START_V1 = "com.huawei.systemmanager.optimize.bootstart.BootStartActivity"
        private const val HUAWEI_SYSTEM_MANAGER_AUTO_START_V2 = "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"
        private const val HUAWEI_SYSTEM_MANAGER_AUTO_START_V3 = "com.huawei.permissionmanager.ui.MainActivity"
    }

    override val isThatRom: Boolean
        get() = "EmotionUI_2.3".equals(getEmuiRomName(), ignoreCase = true) ||
                Build.DISPLAY.contains("emui2.3", ignoreCase = true) ||
                "EMUI 2.3".equals(getEmuiRomName(), ignoreCase = true) ||
                "EmotionUI_3.0".equals(getEmuiRomName(), ignoreCase = true) ||
                "EmotionUI_3.0.1".equals(getEmuiRomName(), ignoreCase = true) ||
                "EmotionUI_3.1".equals(getEmuiRomName(), ignoreCase = true) ||
                "EmotionUI_4.1".equals(getEmuiRomName(), ignoreCase = true) ||
                Build.BRAND.equals(deviceManufacturer.toString(), ignoreCase = true) ||
                Build.MANUFACTURER.equals(deviceManufacturer.toString(), ignoreCase = true) ||
                Build.FINGERPRINT.contains(deviceManufacturer.toString(), ignoreCase = true)

    override val deviceManufacturer: Manufacturer
        get() = Manufacturer.HUAWEI

    override val helpImagePowerSaving: Int
        get() = R.drawable.huawei_powersaving

    override fun isActionPowerSavingAvailable(context: Context) = true

    override fun isActionNotificationAvailable(context: Context) = true

    override fun getActionPowerSaving(context: Context): Intent = ActionsUtils.createIntent().apply {
        action = HUAWEI_ACTION_POWER_SAVING
    }

    override fun getActionNotification(context: Context): Intent = ActionsUtils.createIntent().apply {
        action = HUAWEI_ACTION_NOTIFICATION
    }

    // AUTOSTART not used in huawei
//    override fun getActionAutoStart(context: Context): Intent? {
        /*Intent intent = ActionsUtils.createIntent();
        intent.setAction(HUAWEI_ACTION_AUTOSTART);
        if (ActionsUtils.isIntentAvailable(context, intent)) {
            return intent;
        } else {
            intent = ActionsUtils.createIntent();
            intent.setComponent(getComponentNameAutoStart(context));
            return intent;
        }*/
//    }

//    private fun getComponentNameAutoStart(context: Context): ComponentName {
//        val mVersion = getHuaweiSystemManagerVersion(context)
//        return if (mVersion == 4 || mVersion == 5) {
//            ComponentName(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME, HUAWEI_SYSTEM_MANAGER_AUTO_START_V2)
//        } else if (mVersion == 6) {
//            ComponentName(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME, HUAWEI_SYSTEM_MANAGER_AUTO_START_V3)
//        } else {
//            ComponentName(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME, HUAWEI_SYSTEM_MANAGER_AUTO_START_V1)
//        }
//    }

    override fun getExtraDebugInfo(context: Context): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("ROM_VERSION").append(getEmuiRomName())
        stringBuilder.append("HuaweiSystemManagerVersionMethod:").append(getHuaweiSystemManagerVersion(context))

        var versionStr: String? = ""
        try {
            versionStr = context.packageManager.getPackageInfo(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.e(e)
        }

        stringBuilder.append("HuaweiSystemManagerPackageVersion:").append(versionStr)

        // ----- PACAKGE INFORMATIONS -----
        stringBuilder.append(HUAWEI_ACTION_AUTO_START).append(ActionsUtils.isIntentAvailable(context, HUAWEI_ACTION_AUTO_START))
        stringBuilder.append(HUAWEI_ACTION_POWER_SAVING).append(ActionsUtils.isIntentAvailable(context, HUAWEI_ACTION_POWER_SAVING))
        stringBuilder.append(HUAWEI_ACTION_NOTIFICATION).append(ActionsUtils.isIntentAvailable(context, HUAWEI_ACTION_NOTIFICATION))
        stringBuilder.append(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME + HUAWEI_SYSTEM_MANAGER_AUTO_START_V1).append(ActionsUtils.isIntentAvailable(context, ComponentName(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME, HUAWEI_SYSTEM_MANAGER_AUTO_START_V1)))
        stringBuilder.append(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME + HUAWEI_SYSTEM_MANAGER_AUTO_START_V2).append(ActionsUtils.isIntentAvailable(context, ComponentName(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME, HUAWEI_SYSTEM_MANAGER_AUTO_START_V2)))
        stringBuilder.append(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME + HUAWEI_SYSTEM_MANAGER_AUTO_START_V3).append(ActionsUtils.isIntentAvailable(context, ComponentName(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME, HUAWEI_SYSTEM_MANAGER_AUTO_START_V3)))
        return stringBuilder.toString()
    }

    private fun getHuaweiSystemManagerVersion(context: Context): Int {
        var versionNum = 0
        var thirdPartFirtDigit = 0
        try {
            val info = context.packageManager.getPackageInfo(HUAWEI_SYSTEM_MANAGER_PACKAGE_NAME, 0)
            Timber.tag(Huawei::class.java.name).i("manager info = %s", info.toString())

            val versionTmp = info.versionName.split("\\.").toTypedArray()
            when {
                versionTmp.size >= 2 -> {
                    versionNum = when {
                        versionTmp[0].toInt() == 5 -> 500
                        versionTmp[0].toInt() == 4 -> (versionTmp[0] + versionTmp[1] + versionTmp[2]).toInt()
                        else -> (versionTmp[0] + versionTmp[1]).toInt()
                    }
                }
                versionTmp.size >= 3 -> {
                    thirdPartFirtDigit = versionTmp[2].substring(0, 1).toInt()
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }

        var version = 0
        when {
            versionNum >= 330 -> {
                version = when {
                    versionNum >= 500 -> 6
                    versionNum >= 400 -> 5
                    versionNum >= 331 -> 4
                    else -> if (thirdPartFirtDigit == 6 || thirdPartFirtDigit == 4 || thirdPartFirtDigit == 2) 3 else 2
                }
            }
            versionNum != 0 -> version = 1
        }
        return version
    }
}
