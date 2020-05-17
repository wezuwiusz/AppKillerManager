package com.thelittlefireman.appkillermanager.devices

import com.thelittlefireman.appkillermanager.utils.Manufacturer

class Vivo : Device {

    // TODO multiple intent in a same actions !
    // Starting: Intent { cmp=com.vivo.permissionmanager/.activity.BgStartUpManagerActivity }
    //java.lang.SecurityException: Permission Denial: starting Intent { flg=0x10000000 cmp=com.vivo.permissionmanager/.activity.BgStartUpManagerActivity } from null (pid=28141, uid=2000) not exported from uid 1000
    private val p1 = "com.iqoo.secure"
    private val p1c1 = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"
    private val p1c2 = "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"
    private val p2 = "com.vivo.permissionmanager"
    private val p2c1 = "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"

    // "com.vivo.abe", "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity"
    //com.iqoo.secure.MainGuideActivity ??
    override val isThatRom: Boolean
        get() = false

    override val deviceManufacturer: Manufacturer
        get() = Manufacturer.VIVO

    /*
       @Override
       public List<ComponentName> getAutoStartSettings(Context context) {
           List<ComponentName> componentNames = new ArrayList<>();
           if(ActionsUtils.isPackageExist(context, p1)){
               componentNames.add(new ComponentName(p1,p1c1));
               componentNames.add(new ComponentName(p1,p1c2));
           }
           if(ActionsUtils.isPackageExist(context,p2)){
               componentNames.add(new ComponentName(p2,p2c1));
           }
           return componentNames;
       }*/
}
