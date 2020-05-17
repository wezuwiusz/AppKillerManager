package com.thelittlefireman.appkillermanager.devices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.thelittlefireman.appkillermanager.utils.ActionsUtils
import com.thelittlefireman.appkillermanager.utils.Manufacturer
import com.thelittlefireman.appkillermanager.utils.SystemUtils

class Xiaomi : Device {

    companion object {
        // TODO TEST new Intent().setComponent(ComponentName("com.miui.securitycenter", "com.miui.powercenter.PowerSettings"))
        private const val MIUI_ACTION_PERMS = "miui.intent.action.APP_PERM_EDITOR"
        private const val MIUI_ACTION_PERMS_EXTRA = "extra_pkgname"

        // OPEN AUTO START SETTINGS
        private const val MIUI_ACTION_AUTO_START = "miui.intent.action.OP_AUTO_START"

        // ONE SPECIFIC APP BATTERY SAVER
        private val MIUI_ACTION_POWER_SAVE = arrayOf("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity")

        // OPEN DEFAULT LIST BATTERY SAVER
        private const val MIUI_ACTION_POWER_SAVE_LIST = "miui.intent.action.POWER_HIDE_MODE_APP_LIST"

        private const val MIUI_ACTION_POWER_SAVE_EXTRA_NAME = "package_name"
        private const val MIUI_ACTION_POWER_SAVE_EXTRA_LABEL = "package_label"
    }

    override val manufacturer: Manufacturer
        get() = Manufacturer.XIAOMI

    override val isThatRom: Boolean
        get() {
            return Build.BRAND.equals(manufacturer.toString(), ignoreCase = true) ||
                    Build.MANUFACTURER.equals(manufacturer.toString(), ignoreCase = true) ||
                    Build.FINGERPRINT.contains(manufacturer.toString(), ignoreCase = true)
        }

    override fun isActionPowerSavingAvailable(context: Context) = true

    override fun isActionAutoStartAvailable(context: Context) = true

    override fun getActionAutoStart(context: Context): Intent? = ActionsUtils.createIntent().apply {
        action = MIUI_ACTION_AUTO_START
        putExtra(MIUI_ACTION_POWER_SAVE_EXTRA_NAME, context.packageName)
        putExtra(MIUI_ACTION_POWER_SAVE_EXTRA_LABEL, SystemUtils.getApplicationName(context))
    }

    override fun getActionPowerSaving(context: Context): Intent = ActionsUtils.createIntent().apply {
        component = ComponentName(MIUI_ACTION_POWER_SAVE[0], MIUI_ACTION_POWER_SAVE[1])
        putExtra(MIUI_ACTION_POWER_SAVE_EXTRA_NAME, context.packageName)
        putExtra(MIUI_ACTION_POWER_SAVE_EXTRA_LABEL, SystemUtils.getApplicationName(context))
    }

    /*
    // TODO CHECK IF GETPACKAGENAME IS NAME OF LIB OR APP
    @Override
    public List<Intent> getAutoStartSettings(Context context) {
        List<Intent> intents = new ArrayList<>();
        intents.add(new Intent("miui.intent.action.POWER_HIDE_MODE_APP_LIST").addCategory(Intent.CATEGORY_DEFAULT));
        //com.miui.powerkeeper/com.miui.powerkeeper.ui.HiddenAppsContainerManagementActivity
        intents.add(new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT));
        //com.miui.securitycenter/com.miui.permcenter.autostart.AutoStartManagementActivity
        return intents;
    }

    * new Intent("miui.intent.action.POWER_HIDE_MODE_APP_LIST").addCategory(Intent.CATEGORY_DEFAULT)
    * new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT)
    *

    private static int getMiuiVersion() {
        String version = SystemUtils.getSystemProperty("ro.miui.ui.version.name");
        if (version != null) {
            try {
                return Integer.parseInt(version.substring(1));
            } catch (Exception e) {
                Log.e(Xiaomi.class.getName(), "get miui version code error, version : " + version);
                Log.e(Xiaomi.class.getName(), Log.getStackTraceString(e));
            }
        }
        return -1;
    }

    public Intent miui_V5(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package" , packageName, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public Intent miui_V6(Context context) {
        Intent intent = new Intent(MIUI_ACTION);
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra(MIUI_EXTRA, context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    // MIUI V7
    private Intent miui_V7(Context context) {
        Intent intent = new Intent(MIUI_ACTION);
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra(MIUI_EXTRA, context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    //MIUI V8
    private Intent miui_V8(Context context) {
        Intent intent = new Intent(MIUI_ACTION);
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra(MIUI_EXTRA, context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
*/
}
