package com.thelittlefireman.appkillermanager.managers

import android.content.Context
import android.content.Intent
import com.thelittlefireman.appkillermanager.devices.Device
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.SystemUtils
import timber.log.Timber

object KillerManager {

    enum class Action {
        ACTION_AUTO_START,
        ACTION_POWER_SAVING,
        ACTION_NOTIFICATIONS
    }

    @JvmStatic
    val device: Device?
        get() = DevicesManager.getDevice()

    @JvmStatic
    val isDeviceSupported: Boolean
        get() = DevicesManager.getDevice() != null

    @JvmStatic
    fun isActionAvailable(context: Context, action: Action?) = DevicesManager.getDevice()?.let {
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
    private fun getIntentFromAction(context: Context, action: Action): Intent? {
        val sDevice = DevicesManager.getDevice()
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
                Timber.e("""
                    INTENT NOT FOUND :${ActionsUtils.getExtrasDebugInformations(intent)}
                    Actions: ${action.name}
                    SYSTEM UTILS: ${SystemUtils.getDefaultDebugInformation()}
                    DEVICE: ${sDevice.getExtraDebugInfo(context)}
                    """.trimIndent()
                )
                // Intent not found action failed
                null
            }
        } else {
            // device not found action failed
            Timber.w("DEVICE NOT FOUND. %s", SystemUtils.getDefaultDebugInformation())
            null
        }
    }
}
