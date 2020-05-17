package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer

class OnePlus : Device {

    override val isThatRom: Boolean
        get() = Build.BRAND.equals(manufacturer.toString(), ignoreCase = true) ||
                Build.MANUFACTURER.equals(manufacturer.toString(), ignoreCase = true) ||
                Build.FINGERPRINT.contains(manufacturer.toString(), ignoreCase = true)

    // This is mandatory for new oneplus version android 8
    override fun needToUseAlongWithActionDoseMode(): Boolean = true

    override val manufacturer: Manufacturer
        get() = Manufacturer.ONEPLUS

    override fun getActionAutoStart(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity")
    }

    override fun isActionAutoStartAvailable(context: Context) = true
}
