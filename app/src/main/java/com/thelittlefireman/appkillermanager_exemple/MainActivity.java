package com.thelittlefireman.appkillermanager_exemple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thelittlefireman.appkillermanager.managers.KillerManager;
import com.thelittlefireman.appkillermanager.ui.DialogKillerManagerBuilder;
import com.thelittlefireman.appkillermanager.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindView(R.id.device_name)
    TextView deviceName;
    @BindView(R.id.is_device_supported)
    TextView isDeviceSupported;
    @BindView(R.id.powerSavingManagerButton)
    Button powerSavingManagerButton;
    @BindView(R.id.autoStartManagerButton)
    Button autoStartManagerButton;
    @BindView(R.id.notificationManagerButton)
    Button notificationManagerButton;

    @BindView(R.id.idByDialog)
    AppCompatCheckBox mAppCompatCheckBoxByDialog;

    @BindView(R.id.goToInstructonsActivityButton)
    Button goToInstructonsActivityButton;

    KillerManager.Actions currentAction = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
                        e.printStackTrace();
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
                        e.printStackTrace();
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
                        e.printStackTrace();
                    }
                }
            }
        });

        final MainActivity self = this;
        goToInstructonsActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(self, InstructionsActivity.class);
                self.startActivity(intent);
            }
        });

        deviceName.setText( "Device name: " .concat(KillerManager.getDevice().getDeviceManufacturer().toString()) );
        isDeviceSupported.setText( "Is supported: ".concat(String.valueOf( KillerManager.isDeviceSupported()) ) );

    }

    public void startDialog(KillerManager.Actions actions) {

        try {
            new DialogKillerManagerBuilder(this).setAction(actions).show();

        } catch (DialogKillerManagerBuilder.UnAvailableActionException e) {
            e.printStackTrace();
        } catch (DialogKillerManagerBuilder.UnSupportedDeviceException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {

        super.onResume();
        if (currentAction == KillerManager.Actions.ACTION_AUTOSTART){
            // may show dalog to ask the user about the result of action
            // and store the result in preference
        }else if (currentAction == KillerManager.Actions.ACTION_NOTIFICATIONS){

        }else if (currentAction == KillerManager.Actions.ACTION_POWERSAVING){

        }
        // dont forget to nullify current action
        currentAction = null;
    }


}
