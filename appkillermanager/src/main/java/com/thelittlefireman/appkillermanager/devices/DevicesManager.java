package com.thelittlefireman.appkillermanager.devices;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DevicesManager {

    private static List<DeviceBase> deviceBaseList = new ArrayList<>(Arrays.asList(
            new Huawei(),
            new Samsung()));

    public static DeviceBase getDevice(){
        List<DeviceBase> currentDeviceBase =new ArrayList<>();
        for (DeviceBase deviceBase : deviceBaseList) {
            if(deviceBase.isThatRom()){
                currentDeviceBase.add(deviceBase);
            }
        }
        if(currentDeviceBase.size()>1){
            // TODO : add more informations to debug : ANDROID VERSION, ROM , etc
            Log.i(DevicesManager.class.getName(),"MORE THAN ONE CORRESPONDING");
        }

        if (currentDeviceBase.size()>0) {
            return currentDeviceBase.get(0);
        }else {
            return null;
        }
    }
}
