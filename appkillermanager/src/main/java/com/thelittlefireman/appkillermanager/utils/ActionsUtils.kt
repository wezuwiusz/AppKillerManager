package com.thelittlefireman.appkillermanager.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

object ActionsUtils {
    fun createIntent(): Intent {
        val intent = Intent()
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return intent
    }

    fun getExtrasDebugInformations(intent: Intent?): String {
        val stringBuilder = StringBuilder()
        if (intent != null) {
            stringBuilder.append("intent actions").append(intent.action)
            stringBuilder.append("intent conponent")
            val componentName = intent.component
            if (componentName != null) {
                stringBuilder.append("ComponentName package:").append(componentName.packageName)
                stringBuilder.append("ComponentName class:").append(componentName.className)
            } else {
                stringBuilder.append("ComponentName is null")
            }
        } else {
            stringBuilder.append("intent is null")
        }
        return stringBuilder.toString()
    }

    fun isIntentAvailable(ctx: Context, actionIntent: String): Boolean {
        return isIntentAvailable(ctx, createIntent().setAction(actionIntent))
    }

    fun isIntentAvailable(ctx: Context, componentName: ComponentName): Boolean {
        return isIntentAvailable(ctx, createIntent().setComponent(componentName))
    }

    fun isIntentAvailable(ctx: Context?, intent: Intent?): Boolean {
        return if (ctx != null && intent != null) {
            val mgr = ctx.packageManager
            val list = mgr.queryIntentActivities(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
            list.size > 0
        } else {
            false
        }
    }
}
