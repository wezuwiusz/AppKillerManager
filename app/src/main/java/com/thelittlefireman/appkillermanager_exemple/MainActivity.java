package com.thelittlefireman.appkillermanager_exemple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;

import com.thelittlefireman.appkillermanager.managers.KillerManager;
import com.thelittlefireman.appkillermanager.ui.DialogKillerManagerBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KillerManager.init(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        powerSavingManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAppCompatCheckBoxByDialog.isChecked()) {
                    startDialog(KillerManager.Actions.ACTION_POWERSAVING);
                } else {
                    KillerManager.doActionPowerSaving(MainActivity.this);
                }
            }
        });
        autoStartManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAppCompatCheckBoxByDialog.isChecked()) {
                    startDialog(KillerManager.Actions.ACTION_AUTOSTART);
                } else {
                    KillerManager.doActionAutoStart(MainActivity.this);
                }
            }
        });
        notificationManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAppCompatCheckBoxByDialog.isChecked()) {
                    startDialog(KillerManager.Actions.ACTION_NOTIFICATIONS);
                } else {
                    KillerManager.doActionNotification(MainActivity.this);
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
    }

    public void startDialog(KillerManager.Actions actions) {

        new DialogKillerManagerBuilder().setContext(this).setAction(actions).show();

    }
}
