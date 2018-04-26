# AppKillerManager
Android library to handle flaky App killer manager (Xiaomi, Huawei, letv, ...) and prevent from : not showing notification, services not start at boot, etc 

If you want to help me do not hesitate to test on your phone and add issue if somethings not work properly

Will be soon available on MavenCentral

#Phone tested :
| PHONE | ANDROID | STATE |
| ------ |:----------:| --:|
| Huawei | EMUI 3.0.1 | OK |
| Samsung | Android 7.0 | OK |

#TODO : 
  - Test on all devices
  - Add differents settings for autostartservice/notifications/permissions
  
# Investigation :

Get the current activity name :

```
$> adb shell
$> dumpsys activity activities | grep mFocusedActivity
```

# THANKS TO:
Sylvain BORELLI