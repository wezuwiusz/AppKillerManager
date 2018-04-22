package com.thelittlefireman.appkillermanager_exemple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thelittlefireman.appkillermanager.killerManager.NotificationSettingsManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindView(R.id.settingsButton)
    Button settingsButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        settingsButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationSettingsManager.doAction(MainActivity.this);
            }
        });
    }
}
