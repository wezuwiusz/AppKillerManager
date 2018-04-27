[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)
[ ![Download](https://api.bintray.com/packages/thomas-goureau/maven/AppKillerManager/images/download.svg) ](https://bintray.com/thomas-goureau/maven/AppKillerManager/_latestVersion)
# AppKillerManager
Android library to handle flaky App killer manager (Xiaomi, Huawei, letv, ...) and prevent from : not showing notification, services not start at boot, etc 

If you want to help me do not hesitate to test on your phone and add issue if somethings not work properly

Will be soon available on MavenCentral

## Phone tested :

PHONE | ANDROID | STATE
--- | --- | ---
Huawei HONOR 4X | EMUI 3.0.1 | OK
Huawei P9 LITE | EMUI 3.0.1 | OK
Samsung | Android 7.0 | OK
Xiaomi Mi mix | Android 7.0 | OK

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
[AndroidPopWinPermission](https://programtalk.com/vs/?source=AndroidPopWinPermission/permssion/src/main/java/io/github/bunnbylue/permssion/)