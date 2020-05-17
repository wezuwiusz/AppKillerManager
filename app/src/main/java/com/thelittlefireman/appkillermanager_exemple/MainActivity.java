package com.thelittlefireman.appkillermanager_exemple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.thelittlefireman.appkillermanager.managers.KillerManager;
import com.thelittlefireman.appkillermanager.ui.DialogKillerManagerBuilder;

import timber.log.Timber;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends Activity {
    TextView deviceName;
    TextView isDeviceSupported;
    Button powerSavingManagerButton;
    Button autoStartManagerButton;
    Button notificationManagerButton;
    AppCompatCheckBox mAppCompatCheckBoxByDialog;

    KillerManager.Actions currentAction = null;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree());

        deviceName = (TextView) findViewById(R.id.device_name);
        isDeviceSupported = (TextView) findViewById(R.id.is_device_supported);
        powerSavingManagerButton = findViewById(R.id.powerSavingManagerButton);
        autoStartManagerButton = findViewById(R.id.autoStartManagerButton);
        notificationManagerButton = findViewById(R.id.notificationManagerButton);
        mAppCompatCheckBoxByDialog = (AppCompatCheckBox) findViewById(R.id.idByDialog);


        //ButterKnife.bind(this);
        powerSavingManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAction = KillerManager.Actions.ACTION_POWERSAVING;
                if (mAppCompatCheckBoxByDialog.isChecked()) {
                    startDialog(currentAction);
                } else {
                    try {
                        KillerManager.doActionPowerSaving(MainActivity.this);
                    } catch (KillerManager.NoActionFoundException e) {
                        Timber.e(e);
                    }
                }
            }
        });
        autoStartManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAction = KillerManager.Actions.ACTION_AUTOSTART;
                if (mAppCompatCheckBoxByDialog.isChecked()) {
                    startDialog(currentAction);
                } else {
                    try {
                        KillerManager.doActionAutoStart(MainActivity.this);
                    } catch (KillerManager.NoActionFoundException e) {
                        Timber.e(e);
                    }
                }
            }
        });
        notificationManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAction = KillerManager.Actions.ACTION_NOTIFICATIONS;
                if (mAppCompatCheckBoxByDialog.isChecked()) {
                    startDialog(currentAction);
                } else {
                    try {
                        KillerManager.doActionNotification(MainActivity.this);
                    } catch (KillerManager.NoActionFoundException e) {
                        Timber.e(e);
                    }
                }
            }
        });

        if (KillerManager.isDeviceSupported()) {
            isDeviceSupported.setText("Is supported: true");
            deviceName.setText("Device name: ".concat(KillerManager.getDevice().getDeviceManufacturer().toString()));
        }

    }

    public void startDialog(KillerManager.Actions actions) {

        try {
            new DialogKillerManagerBuilder(this).setAction(actions).show();

        } catch (DialogKillerManagerBuilder.UnAvailableActionException e) {
            Timber.e(e);
        } catch (DialogKillerManagerBuilder.UnSupportedDeviceException e) {
            Timber.e(e);
        }

    }

    @Override
    public void onResume() {

        super.onResume();
        if (currentAction == KillerManager.Actions.ACTION_AUTOSTART) {
            // may show dalog to ask the user about the result of action
            // and store the result in preference
        } else if (currentAction == KillerManager.Actions.ACTION_NOTIFICATIONS) {

        } else if (currentAction == KillerManager.Actions.ACTION_POWERSAVING) {

        }
        // dont forget to nullify current action
        currentAction = null;
    }


}
