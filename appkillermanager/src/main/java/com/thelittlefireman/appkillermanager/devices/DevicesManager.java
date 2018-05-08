package com.thelittlefireman.appkillermanager.devices;

import com.thelittlefireman.appkillermanager.utils.LogUtils;
import com.thelittlefireman.appkillermanager.utils.SystemUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DevicesManager {

    private static List<DeviceBase> deviceBaseList = new ArrayList<>(Arrays.asList(
            new Xiaomi(),
            new OnePlus(),
            new Huawei(),
            new Meizu(),
            new Samsung()));

    public static DeviceBase getDevice(){
        List<DeviceBase> currentDeviceBase =new ArrayList<>();
        for (DeviceBase deviceBase : deviceBaseList) {
            if(deviceBase.isThatRom()){
                currentDeviceBase.add(deviceBase);
            }
        }
        if(currentDeviceBase.size()>1){
            String logDevices="";
            for (DeviceBase deviceBase : currentDeviceBase) {
                logDevices+=deviceBase.getDeviceManufacturer();
            }
            LogUtils.e(DevicesManager.class.getName(),"MORE THAN ONE CORRESPONDING:"+logDevices+"|"+
                    SystemUtils.getDefaultDebugInformation());
        }

        if (currentDeviceBase.size()>0) {
            return currentDeviceBase.get(0);
        }else {
            return null;
        }
    }
}
