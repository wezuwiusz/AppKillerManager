package com.thelittlefireman.appkillermanager.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

public class DialogKillerManager extends AlertDialog.Builder{

    public DialogKillerManager(@NonNull Context context) {
        super(context);
    }

    public DialogKillerManager(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
}


/*
* final SharedPreferences settings = getSharedPreferences("ProtectedApps", MODE_PRIVATE);
    final String saveIfSkip = "skipProtectedAppsMessage";
    boolean skipMessage = settings.getBoolean(saveIfSkip, false);
    if (!skipMessage) {
        final SharedPreferences.Editor editor = settings.edit();
        Intent intent = new Intent();
        intent.setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");
        if (isCallable(intent)) {
            final AppCompatCheckBox dontShowAgain = new AppCompatCheckBox(this);
            dontShowAgain.setText("Do not show again");
            dontShowAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    editor.putBoolean(saveIfSkip, isChecked);
                    editor.apply();
                }
            });

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Huawei Protected Apps")
                    .setMessage(String.format("%s requires to be enabled in 'Protected Apps' to function properly.%n", getString(R.string.app_name)))
                    .setView(dontShowAgain)
                    .setPositiveButton("Protected Apps", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            huaweiProtectedApps();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show();
        } else {
            editor.putBoolean(saveIfSkip, true);
            editor.apply();
        }*/