package com.thelittlefireman.appkillermanager.managers

import com.thelittlefireman.appkillermanager.devices.*
import com.thelittlefireman.appkillermanager.utils.SystemUtils
import timber.log.Timber

object DevicesManager {

    private val devices = listOf(
            Asus(),
            Huawei(),
            Letv(),
            Meizu(),
            OnePlus(),
            HTC(),
            Samsung(),
            Xiaomi(),
            ZTE()
    )

    @JvmStatic
    fun getDevice(): Device? {
        val currentDevice = devices.filter { it.isThatRom }

        if (currentDevice.size > 1) {
            val logDevices = currentDevice.joinToString(", ") {
                it.manufacturer.toString()
            }

            Timber.w("More than one corresponding device: %s. Debug info: %s", logDevices, SystemUtils.getDefaultDebugInformation())
        }

        return currentDevice.firstOrNull()
    }
}
