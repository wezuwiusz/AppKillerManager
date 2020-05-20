package com.thelittlefireman.appkillermanager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.thelittlefireman.appkillermanager.devices.*
import com.thelittlefireman.appkillermanager.exceptions.NoActionFoundException
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.SystemUtils
import timber.log.Timber

object AppKillerManager {

    enum class Action {
        ACTION_AUTO_START,
        ACTION_POWER_SAVING,
        ACTION_NOTIFICATIONS
    }

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
    fun isDeviceSupported() = getDevice() != null

    @JvmStatic
    fun getDevice(): Device? {
        val currentDevice = devices.filter { it.isThatRom }

        if (currentDevice.size > 1) {
            val logDevices = currentDevice.joinToString(", ") {
                it.manufacturer.toString()
            }

            Timber.w("More than one corresponding device: %s. Debug info: %s", logDevices, SystemUtils.defaultDebugInformation)
        }

        return currentDevice.firstOrNull()
    }

    @JvmStatic
    fun isActionAvailable(context: Context, action: Action?) = getDevice()?.let {
        when (action) {
            Action.ACTION_AUTO_START -> it.isActionAutoStartAvailable(context)
            Action.ACTION_POWER_SAVING -> it.isActionPowerSavingAvailable(context)
            Action.ACTION_NOTIFICATIONS -> it.isActionNotificationAvailable(context)
            else -> false
        }
    } ?: false

    @JvmStatic
    fun isAnyActionAvailable(context: Context) = Action.values().any { isActionAvailable(context, it) }

    @JvmStatic
    @Throws(NoActionFoundException::class)
    fun doActionAutoStart(context: Context) {
        doAction(context, Action.ACTION_AUTO_START)
    }

    @JvmStatic
    @Throws(NoActionFoundException::class)
    fun doActionNotification(context: Context) {
        doAction(context, Action.ACTION_NOTIFICATIONS)
    }

    @JvmStatic
    @Throws(NoActionFoundException::class)
    fun doActionPowerSaving(context: Context) {
        doAction(context, Action.ACTION_POWER_SAVING)
    }

    /**
     * Execute the action
     *
     * @param context the current context
     * @param action the wanted action to execute
     */
    @Throws(NoActionFoundException::class)
    private fun doAction(context: Context, action: Action) {
        // Avoid main app to crash when intent denied by using try catch
        try {
            val intent = getIntentFromAction(context, action)
            if (intent != null && ActionsUtils.isIntentAvailable(context, intent)) {
                context.startActivity(intent)
                // Intent found action succeed
            }
        } catch (e: Exception) {
            // Exception handle action failed
            Timber.e(e)
            throw NoActionFoundException()
        }
    }

    /**
     * Return the intent for a specific action
     *
     * @param context the current context
     * @param action the wanted actions
     * @return the intent
     */
    @SuppressLint("BinaryOperationInTimber")
    private fun getIntentFromAction(context: Context, action: Action): Intent? {
        val sDevice = getDevice()
        return if (sDevice != null) {
            val intent = when (action) {
                Action.ACTION_AUTO_START -> sDevice.getActionAutoStart(context)
                Action.ACTION_POWER_SAVING -> sDevice.getActionPowerSaving(context)
                Action.ACTION_NOTIFICATIONS -> sDevice.getActionNotification(context)
            }
            if (intent != null && ActionsUtils.isIntentAvailable(context, intent)) {
                // Intent found action succeed
                intent
            } else {
                Timber.e("INTENT NOT FOUND :${ActionsUtils.getExtrasDebugInformations(intent)}\n" +
                        "Actions: ${action.name}\n" +
                        "DEVICE: ${sDevice.getExtraDebugInfo(context)}\n" +
                        "SYSTEM UTILS: ${SystemUtils.defaultDebugInformation}")
                // Intent not found action failed
                null
            }
        } else {
            // device not found action failed
            Timber.w("DEVICE NOT FOUND. %s", SystemUtils.defaultDebugInformation)
            null
        }
    }
}
