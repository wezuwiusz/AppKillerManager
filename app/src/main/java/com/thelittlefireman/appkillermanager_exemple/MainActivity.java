package com.thelittlefireman.appkillermanager_exemple;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.thelittlefireman.appkillermanager.AppKillerManager;
import com.thelittlefireman.appkillermanager.exceptions.NoActionFoundException;
import com.thelittlefireman.appkillermanager.ui.DialogKillerManagerBuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class MainActivity extends Activity {
    TextView logs;
    TextView deviceName;
    TextView isDeviceSupported;
    Button powerSavingManagerButton;
    Button autoStartManagerButton;
    Button notificationManagerButton;
    AppCompatCheckBox mAppCompatCheckBoxByDialog;

    AppKillerManager.Action currentAction = null;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logs = findViewById(R.id.logs);
        Timber.plant(new Timber.DebugTree());
        Timber.plant(new Timber.Tree() {
            @SuppressLint("SetTextI18n")
            @Override
            protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
                logs.setText(tag + ": " + message + "\n" + logs.getText());
            }
        });

        deviceName = findViewById(R.id.device_name);
        isDeviceSupported = findViewById(R.id.is_device_supported);
        powerSavingManagerButton = findViewById(R.id.powerSavingManagerButton);
        autoStartManagerButton = findViewById(R.id.autoStartManagerButton);
        notificationManagerButton = findViewById(R.id.notificationManagerButton);
        mAppCompatCheckBoxByDialog = findViewById(R.id.idByDialog);


        //ButterKnife.bind(this);
        powerSavingManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAction = AppKillerManager.Action.ACTION_POWER_SAVING;
                if (mAppCompatCheckBoxByDialog.isChecked()) {
                    startDialog(currentAction);
                } else {
                    try {
                        AppKillerManager.doActionPowerSaving(MainActivity.this);
                    } catch (NoActionFoundException e) {
                        Timber.e(e);
                    }
                }
            }
        });
        autoStartManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAction = AppKillerManager.Action.ACTION_AUTO_START;
                if (mAppCompatCheckBoxByDialog.isChecked()) {
                    startDialog(currentAction);
                } else {
                    try {
                        AppKillerManager.doActionAutoStart(MainActivity.this);
                    } catch (NoActionFoundException e) {
                        Timber.e(e);
                    }
                }
            }
        });
        notificationManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAction = AppKillerManager.Action.ACTION_NOTIFICATIONS;
                if (mAppCompatCheckBoxByDialog.isChecked()) {
                    startDialog(currentAction);
                } else {
                    try {
                        AppKillerManager.doActionNotification(MainActivity.this);
                    } catch (NoActionFoundException e) {
                        Timber.e(e);
                    }
                }
            }
        });

        if (AppKillerManager.isDeviceSupported()) {
            isDeviceSupported.setText("Is supported: true");
            deviceName.setText("Device name: ".concat(AppKillerManager.getDevice().getManufacturer().toString()));
        }

    }

    public void startDialog(AppKillerManager.Action action) {

        try {
            new DialogKillerManagerBuilder(this).setAction(action).show();

        } catch (DialogKillerManagerBuilder.UnAvailableActionException e) {
            Timber.e(e);
        } catch (DialogKillerManagerBuilder.UnSupportedDeviceException e) {
            Timber.e(e);
        }

    }

    @Override
    public void onResume() {

        super.onResume();
        if (currentAction == AppKillerManager.Action.ACTION_AUTO_START) {
            // may show dalog to ask the user about the result of action
            // and store the result in preference
        } else if (currentAction == AppKillerManager.Action.ACTION_NOTIFICATIONS) {

        } else if (currentAction == AppKillerManager.Action.ACTION_POWER_SAVING) {

        }
        // dont forget to nullify current action
        currentAction = null;
    }
}
