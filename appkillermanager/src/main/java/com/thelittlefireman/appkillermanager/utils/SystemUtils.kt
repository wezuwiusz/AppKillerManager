package com.thelittlefireman.appkillermanager.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process
import android.os.UserManager
import androidx.annotation.RequiresApi
import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object SystemUtils {
    val defaultDebugInformation: String
        get() = "Display_id: ${Build.DISPLAY}\n" +
                "MODEL: ${Build.MODEL}\n" +
                "MANUFACTURER: ${Build.MANUFACTURER}\n" +
                "PRODUCT: ${Build.PRODUCT}"

    val emuiRomName: String?
        get() = try {
            getSystemProperty("ro.build.version.emui")
        } catch (e: Exception) {
            ""
        }

    fun getApplicationName(context: Context): String {
        val packageManager = context.packageManager
        var applicationInfo: ApplicationInfo? = null
        try {
            applicationInfo =
                packageManager.getApplicationInfo(context.applicationInfo.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return (if (applicationInfo != null) packageManager.getApplicationLabel(applicationInfo) else "Unknown") as String
    }

    val miuiRomName: String?
        get() = try {
            getSystemProperty("ro.miui.ui.version.name")
        } catch (e: Exception) {
            ""
        }

    private fun getSystemProperty(propName: String): String? {
        val line: String
        var input: BufferedReader? = null
        try {
            val p = Runtime.getRuntime().exec("getprop $propName")
            input = BufferedReader(InputStreamReader(p.inputStream), 1024)
            line = input.readLine()
            input.close()
        } catch (ex: IOException) {
            Timber.e(ex, "Unable to read system property %s", propName)
            return null
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    Timber.e(e, "Exception while closing InputStream")
                }
            }
        }
        return line
    }
    // INFO http://imsardine.simplbug.com/note/android/adb/commands/am-start.html
    /**
     * Open an Activity by using Application Manager System (prevent from crash permission exception)
     *
     * @param context         current application Context
     * @param packageName     pacakge name of the target application (exemple: com.huawei.systemmanager)
     * @param activityPackage activity name of the target application (exemple: .optimize.process.ProtectActivity)
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun startActivityByAMSystem(context: Context, packageName: String, activityPackage: String) {
        var cmd = "am start -n $packageName/$activityPackage"
        val um = context.getSystemService(Context.USER_SERVICE) as UserManager
        cmd += " --user " + um.getSerialNumberForUser(Process.myUserHandle())
        Runtime.getRuntime().exec(cmd)
    }

    /**
     * Open an Action by using Application Manager System (prevent from crash permission exception)
     *
     * @param context      current application Context
     * @param intentAction action of the target application (exemple: com.huawei.systemmanager)
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun startActionByAMSystem(context: Context, intentAction: String) {
        var cmd = "am start -a $intentAction"
        val um = context.getSystemService(Context.USER_SERVICE) as UserManager
        cmd += " --user " + um.getSerialNumberForUser(Process.myUserHandle())
        Runtime.getRuntime().exec(cmd)
    }
}
