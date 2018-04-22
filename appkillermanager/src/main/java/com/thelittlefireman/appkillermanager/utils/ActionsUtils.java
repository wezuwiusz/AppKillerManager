package com.thelittlefireman.appkillermanager.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;

import java.util.List;

public class ActionsUtils {

    public static Intent createIntent() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public static boolean isIntentAvailable(@NonNull Context ctx, @NonNull Intent intent) {
        if (ctx != null && intent != null) {
            final PackageManager mgr = ctx.getPackageManager();
            List<ResolveInfo> list =
                    mgr.queryIntentActivities(intent,
                            PackageManager.MATCH_DEFAULT_ONLY);
            return list != null && list.size() > 0;
        } else {
            return false;
        }
    }
}
