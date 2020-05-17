package com.thelittlefireman.appkillermanager.devices

import android.os.Build
import com.thelittlefireman.appkillermanager.utils.Manufacturer

class Oppo : Device {

    companion object {
        // TODO multiple intent in a same actions need to be refractor!
        /** java.lang.SecurityException: Permission Denial: starting Intent { cmp=com.coloros.safecenter/.startupapp.StartupAppListActivity } from ProcessRecord{7eba0ba 27527:crb.call.follow.mycrm/u0a229} (pid=27527, uid=10229) requires oppo.permission.OPPO_COMPONENT_SAFE*/
        //coloros3.0
        private const val p1 = "com.coloros.safecenter"
        private const val p1c1 = "com.coloros.safecenter.permission.startup.StartupAppListActivity"
        private const val p1c2 = "com.coloros.safecenter.startupapp.StartupAppListActivity"
        private const val p12 = "com.coloros.oppoguardelf"
        private const val p12c1 = "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity"
        private const val p12c2 = "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity"

        //OLD == ColorOS V2.1
        private const val p13 = "com.color.oppoguardelf"
        private const val p13c1 = "com.color.safecenter.permission.startup.StartupAppListActivity"
        private const val p13c2 = "com.color.safecenter.startupapp.StartupAppListActivity"
        private const val p2 = "com.oppo.safe"
        private const val p2c1 = "com.oppo.safe.permission.startup.StartupAppListActivity"
    }

    override val isThatRom: Boolean
        get() = Build.BRAND.equals(deviceManufacturer.toString(), ignoreCase = true) ||
                Build.MANUFACTURER.equals(deviceManufacturer.toString(), ignoreCase = true) ||
                Build.FINGERPRINT.contains(deviceManufacturer.toString(), ignoreCase = true)

    override val deviceManufacturer: Manufacturer
        get() = Manufacturer.OPPO

/*
    private ComponentName getComponentName(Context context){
        if(ActionsUtils.isPackageExist(context,p1)){

        }
        else if(ActionsUtils.isPackageExist(context,p12)){

        }
        else if(ActionsUtils.isPackageExist(context,p13)){

        }
        else if(ActionsUtils.isPackageExist(context,p2)){

        }
        return null;
    }*/
}
