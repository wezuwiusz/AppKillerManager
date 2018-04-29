package com.thelittlefireman.appkillermanager_exemple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thelittlefireman.appkillermanager.killerManager.KillerManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindView(R.id.powerSavingManagerButton)
    Button powerSavingManagerButton;
    @BindView(R.id.autoStartManagerButton)
    Button autoStartManagerButton;
    @BindView(R.id.notificationManagerButton)
    Button notificationManagerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        powerSavingManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KillerManager.doActionAutoStart(MainActivity.this);
            }
        });
        autoStartManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KillerManager.doActionAutoStart(MainActivity.this);
            }
        });
        notificationManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KillerManager.doActionNotification(MainActivity.this);
            }
        });
    }
}
