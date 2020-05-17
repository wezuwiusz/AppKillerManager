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
    fun getDevice(): DeviceBase? {
        val currentDeviceBase = devices.filter { it.isThatRom }

        if (currentDeviceBase.size > 1) {
            val logDevices = currentDeviceBase.joinToString(", ") {
                it.deviceManufacturer.toString()
            }

            Timber.w("More than one corresponding device: %s. Debug info: %s", logDevices, SystemUtils.getDefaultDebugInformation())
        }

        return currentDeviceBase.firstOrNull()
    }
}
