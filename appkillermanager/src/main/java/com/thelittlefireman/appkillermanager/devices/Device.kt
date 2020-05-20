package com.thelittlefireman.appkillermanager.devices

import android.content.Context
import android.content.Intent
import com.thelittlefireman.appkillermanager.utils.Manufacturer

interface Device {

    val isThatRom: Boolean

    val manufacturer: Manufacturer

    fun isActionPowerSavingAvailable(context: Context): Boolean = false

    fun isActionAutoStartAvailable(context: Context): Boolean = false

    fun isActionNotificationAvailable(context: Context): Boolean = false

    fun needToUseAlongWithActionDoseMode(): Boolean = false

    fun getActionPowerSaving(context: Context): Intent? = null

    fun getActionAutoStart(context: Context): Intent? = null

    // FIXME IS IT REALLY NEEDED ? ==> REPLACE BY OTHER FUNCTION ?
    fun getActionNotification(context: Context): Intent? = null

    // TODO ADD FOR MEMORY OPTIMIZATION : https://github.com/00aj99/CRomAppWhitelist
    fun getExtraDebugInfo(context: Context): String? = null
}
