[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)
[ ![Download](https://api.bintray.com/packages/thomas-goureau/maven/AppKillerManager/images/download.svg) ](https://bintray.com/thomas-goureau/maven/AppKillerManager/_latestVersion)
# AppKillerManager

Android library to handle flaky App killer manager (Xiaomi, Huawei, letv, ...) and prevent from : not showing notification, services not start at boot, etc 

If you want to help me do not hesitate to test on your phone and add issue if somethings not work properly

Will be soon available on Jcenter

Android Custom Roms made sometimes your apps unfunctional due to :

* Your App is killed when it's not in foreground
* Notification message do not appear
* Your services is killed by power saving mode

Rom concerned : Samsung, Huawei, Xiaomi, 

## Phone tested :
(EasyMode) = Go directly to pacakge ?

PHONE | ANDROID OS | CUSTOM ROM | AutoStart(EasyMode) | PowerSavingMode(EasyMode) |
--- | --- | --- | --- | ---
Huawei HONOR 4X | Android 4.4 | EMUI 3.0.1 | | OK (No)
Huawei P9 LITE | Android 6.0 | EMUI 4.1 | | OK (No)
Samsung | Android 7.0 | | N/A | OK
Xiaomi Mi mix | Android 6.0.1 | MIUI 8.0 | OK (No) | OK (Yes)


ANDROID OS | CUSTOM ROM | AutoStart EasyMode | AutoStart List | PowerSavingMode EasyMode | PowerSavingMode List
--- | --- | --- | --- | --- | ---
Android 4.4 | EMUI 3.0.1 | | | ACTION huawei.intent.action.HSM_PROTECTED_APPS |
Android 6.0.1 | MIUI 8.0 | | ACTION miui.intent.action.OP_AUTO_START | INTENT "com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity"  extras : package_name,package_level | ACTION miui.intent.action.POWER_HIDE_MODE_APP_LIST

## Usage
### Step 1

##### Gradle

```groovy
dependencies {
    implementation 'com.thelittlefireman.appkillermanager:AppKillerManager:0.0.1'
}
```

## Maintainers
[![thelittlefireman](https://avatars2.githubusercontent.com/u/5165783?s=40&v=4) thelittlefireman](https://github.com/thelittlefireman) 

## TODO : 
  - Test on all devices
  - Add differents settings for autostartservice/notifications/permissions
  
## HELPING INFORMATIONS :

Get the current activity name :

```
$> adb shell
$> dumpsys activity activities | grep mFocusedActivity
```

## THANKS TO:
Sylvain BORELLI

## SOURCES/HELPING TOOLS :
[backgroundable-android](https://github.com/dirkam/backgroundable-android)

[TamingTask](https://github.com/YougaKing/TamingTask)

[CRomAppWhitelist](https://github.com/WanghongLin/CRomAppWhitelist)

[permission](https://github.com/by123/permission)

[AndroidPopWinPermission](https://programtalk.com/vs/?source=AndroidPopWinPermission/permssion/src/main/java/io/github/bunnbylue/permssion/)