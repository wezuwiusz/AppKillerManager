package com.thelittlefireman.appkillermanager.devices

import androidx.annotation.DrawableRes

abstract class Device : DeviceBase {
    override fun needToUseAlongWithActionDoseMode(): Boolean {
        return false
    }

    @DrawableRes
    override fun getHelpImageAutoStart(): Int {
        return 0
    }

    @DrawableRes
    override fun getHelpImageNotification(): Int {
        return 0
    }

    @DrawableRes
    override fun getHelpImagePowerSaving(): Int {
        return 0
    }
}
