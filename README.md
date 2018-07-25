[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)
[ ![Download](https://api.bintray.com/packages/thomas-goureau/maven/AppKillerManager/images/download.svg) ](https://bintray.com/thomas-goureau/maven/AppKillerManager/_latestVersion)
# AppKillerManager

Android library to handle App killer manager or agressive power saving mode (Xiaomi, Huawei, letv, ...) and prevent from : not showing notification, services not start at boot, etc 

This library will open the right settings of the user phone and prompt him to add your app to whitelist.

Android Custom Roms made sometimes your apps unfunctional due to :

* Your App is killed when it's not in foreground
* Notification message do not appear
* Your services is killed by agressive power saving mode

###If you want to help me do not hesitate to test on your phone and add issue if somethings not work properly

## Current Compatibility :

* Samsung (<span style="color:green">TESTED</span>)
* Huawei (<span style="color:green">TESTED</span>)
* Xiaomi (<span style="color:green">TESTED</span>)
* Meizu (<span style="color:red"> NOT TESTED</span>)
* OnePlus (<span style="color:red">NOT TESTED</span>)
* Letv (<span style="color:red">NOT TESTED</span>)

### TODO
Add :
* Oppo
* Vivo
* Asus

* Add hability to customise dialog

* Add screenshot and "settings path" of the intent action for all phones on ReadMe

## Usage
### Step 1

##### Add it on your Android app

```groovy
dependencies {
    implementation 'com.thelittlefireman.appkillermanager:AppKillerManager:0.0.1'
}
```

### Step 2

User with a custom dialog:
```Java
    public void startDialog(KillerManager.Actions actions) {
        new DialogKillerManagerBuilder().setContext(this).setAction(actions).show();
    }
```

Or use it directly :
```Java
// Open the corresponding Power Saving Settings
KillerManager.doActionPowerSaving(MyContext);
```
```Java
// Open the corresponding Auto Start permission Settings
KillerManager.doActionAutoStart(MyContext);
```
```Java
// Open the corresponding Notification permission Settings
KillerManager.doActionNotification(MyContext);
```

## Maintainers
[thelittlefireman](https://github.com/thelittlefireman) 

## TODO : 
  - Test on all devices
  - Add differents settings for autostartservice/notifications/permissions
  - Add custom messages for more explaination on what user need to do on manufacturer "settings Activity"
  
## DEBUG/HELPING INFORMATIONS :

###Get the current activity name :

```shell
$> adb shell
$> dumpsys activity activities | grep mFocusedActivity
or to get more result
$> dumpsys activity activities | grep Activity
```

###Start an activity :

```shell
$> adb shell
$> #by component name
$> am start -n com.samsung.memorymanager/com.samsung.memorymanager.RamActivity  --user 0
$> #by action
$> am start -a com.exemple.Action --user 0
```
more information http://imsardine.simplbug.com/note/android/adb/commands/am-start.html

### Phone tested :
(EasyMode) = Go directly to package (app) ?

PHONE | ANDROID OS | CUSTOM ROM | AutoStart(EasyMode) | PowerSavingMode(EasyMode) |
--- | --- | --- | --- | ---
Huawei HONOR 4X | Android 4.4 | EMUI 3.0.1 | | OK (No)
Huawei P9 LITE | Android 6.0 | EMUI 4.1 | | OK (No)
Samsung | Android 7.0 | | N/A | OK
Xiaomi Mi mix | Android 6.0.1 | MIUI 8.0 | OK (No) | OK (Yes)

//TODO TEST SAMSUNG 8.0.1, ASUS 7.1, Huawei Android 6.0.1


ANDROID OS | CUSTOM ROM | AutoStart EasyMode | AutoStart List | PowerSavingMode EasyMode | PowerSavingMode List
--- | --- | --- | --- | --- | ---
Android 4.4 | EMUI 3.0.1 | | | ACTION huawei.intent.action.HSM_PROTECTED_APPS |
Android 6.0.1 | MIUI 8.0 | | ACTION miui.intent.action.OP_AUTO_START | INTENT "com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity"  extras : package_name,package_level | ACTION miui.intent.action.POWER_HIDE_MODE_APP_LIST

#### Related View :

Tab of which activity is open when you call functions

FUNCTIONS | Huawei | Samsung
--- | --- |---
Power Saving Settings | ![Huawei](IMG/huawei.png?raw=true "Huawei")<!-- .element height="130px" --> |
Auto Start permission Settings | |
Notification permission Settings | |


## THANKS TO:
Sylvain BORELLI

## SOURCES/HELPING TOOLS :
[backgroundable-android](https://github.com/dirkam/backgroundable-android)

[TamingTask](https://github.com/YougaKing/TamingTask)

[CRomAppWhitelist](https://github.com/WanghongLin/CRomAppWhitelist)

[permission](https://github.com/by123/permission)

[AndroidPopWinPermission](https://programtalk.com/vs/?source=AndroidPopWinPermission/permssion/src/main/java/io/github/bunnbylue/permssion/)