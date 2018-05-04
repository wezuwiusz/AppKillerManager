Our App Usage, aProfiles and 2 Battery apps keep running in the background for monitoring the device state. Some devices built-in a task killer function which kills our apps in the background and results in it stops to work. To fix the problem, please go through system settings or built-in apps to add our apps to the whitelist.

Here listed some examples about how to add our apps to the whitelist:

Asus devices:
================================
. Launcher > Settings > Power management > Auto-start manager > tap over the DOWNLOADED page > switch to "Allow" for our apps

Htc devices
================================
. Apps drawer > Boost+ > Optimize background apps > tap our apps > select "Off"

Huawei devices:
================================
. Apps drawer > Phone Manager > Battery manager(or Energy Saver) > Protected apps > Turn on the switch for our app
or
* Launcher > Settings > tap "All" tab > Protected apps > Turn on the switch for our app

Lenovo devices:
================================
. Launcher > Settings > Power manager > Background app management > add our apps to the unrestricted (or allow auto-start) list

OnePlus devices:
================================
. Launcher > Settings > Apps > Gear icon > Apps Auto-launch > allow our apps to start-up in the background
if ("oneplus".equalsIgnoreCase(manufacturer)) { intent.setComponent(new ComponentName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity")); }

Oppo devices:
================================
. Apps drawer > Security center > Privacy permissions > Auto-run management (or Startup manager) > Turn on the switch for our apps
and
. Launcher > Settings > Application management > Running tab > tap the Locker icon for our apps

Samsung devices:
================================
. Launcher > Settings > Security > Auto-start management > set our apps to 'Allowed'
or
. Launcher > Settings > Battery > Detail > tap our app > Turn off
or
. Apps drawer > Smart manager > Battery > Detail > tap our app > Turn off
or
. Launcher > Settings > Battery > Battery usage > MENU > Optimize battery usage > select "All apps" > turn off the switch for our apps

Sony devices:
================================
. Launcher > Settings > Storage & memory > turn off the "Smart cleaner" option or continue to tap the three dots at the top right > Advanced > add our apps to the whitelist

Vivo devices:
================================
. Apps drawer > iManager > App manager > Autostart manager > allow our apps to auto-start in the background

Xiaomi / MIUI devices:
================================
. Apps drawer > Security app > Permissions > tap Autostart > allow our app to autostart by turning on the switch
or
. Launcher > Settings > Additional Settings > Battery and Performance > Manage apps' battery usage > turn "Power saving modes" off > open the Security app > navigate to Permissions > add our app to Autostart > invoke Task Manager > find our app > drag it downwards until the padlock icon appears


Android 6 or greater devices:
================================
If the device runs on Android 6 or greater version, please continue to add our apps to the not optimized list of Doze mode:

. Launcher > Settings > Apps > Advanced settings toolbar icon > Battery optimization > select "All apps" > tap our app > select "Don't optimize" for it
or
. Launcher > Settings > Apps > Advanced settings toolbar icon > Special access > Battery optimization > select "All apps" > tap our app > select "Don't optimize" for it
or
. Launcher > Settings > Apps > Advanced settings toolbar icon > Ignore optimizations > select "All apps" > tap our app > select "Allow" for it
or
. Launcher > Settings > Battery > MENU > Battery optimization > select "All apps" > tap our app > select "Don't optimize" for it

Android 8 or greater devices:
================================
If the device runs on Android 8 or greater version, please continue to add our apps to the not optimized list of Doze mode by reading above "Android 6 or greater devices" section.

In addition, you have to enable our apps to be run in the background:
. Launcher > Settings > Apps > App Info > tap our app > Battery > turn on the "Background activity" option

If you have the instruction for other devices, please let us know.