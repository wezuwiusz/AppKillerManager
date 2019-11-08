package com.thelittlefireman.appkillermanager_exemple;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.thelittlefireman.appkillermanager.managers.CallWrapper;
import com.thelittlefireman.appkillermanager.managers.InstructionsManager;
import com.thelittlefireman.appkillermanager.managers.KillerManager;
import com.thelittlefireman.appkillermanager.managers.ResponseWrapper;
import com.thelittlefireman.appkillermanager.ui.DialogKillerManagerBuilder;
import com.thelittlefireman.appkillermanager.ui.InstructionsViewer;
import com.thelittlefireman.appkillermanager.utils.Instructions;
import com.thelittlefireman.appkillermanager.utils.LogUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionsActivity extends AppCompatActivity {
    private static String TAG = "InstructionsActivity";

    @BindView(R.id.instructionsManagerButton)
    Button instructionsManagerButton;
    @BindView(R.id.viewInstructionsButton)
    Button viewInstructionsButton;
    @BindView(R.id.viewerLayout)
    LinearLayout vieweLayout;
    @BindView(R.id.manufactureName)
    EditText manufactureName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KillerManager.init(this);
        setContentView(R.layout.activity_instructions);
        ButterKnife.bind(this);

        instructionsManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InstructionsManager()
                        .setOnStart(new InstructionsManager.OnStart() {
                            @Override
                            public void onStart() {
                                LogUtils.i(TAG, "Start getting manufacturer instructions");
                            }
                        })
                        .setOnSuccess(new InstructionsManager.OnSuccess() {
                            @Override
                            public void onSuccess(@NonNull ResponseWrapper responseWrapper) {
                                Instructions instructions = new Instructions(responseWrapper);
                                if ( ! instructions.isHasException()){
                                    //LogUtils.i(TAG,instructions.getName() + instructions.getUser_solution());
                                    //Do your stuff here
                                }
                            }

                            @Override
                            public void onSuccess(@NonNull Instructions instructions) {
                                if ( ! instructions.isHasException()){
                                    LogUtils.i(TAG,instructions.getName() + instructions.getUser_solution());
                                    //Do your stuff here
                                }
                            }
                        })
                        .setOnFailed(new InstructionsManager.OnFailed() {
                            @Override
                            public void onFailed(CallWrapper callWrapper,
                                                 ResponseWrapper responseWrapper,
                                                 IOException e) {
                                LogUtils.i(TAG, "Is Call cancelled?" + callWrapper.isCanceled());
                                LogUtils.i(TAG, "Is Call Executed?" + callWrapper.isExecuted());
                                LogUtils.i(TAG, "Response message" + responseWrapper.getMessage());
                                LogUtils.i(TAG, "Is successfull ?" + responseWrapper.isSuccessful());
                                LogUtils.i(TAG, "Is redirect ?" + responseWrapper.isRedirect());
                            }
                        })
                        .getManufacturerInstructions();
            }
        });

        final InstructionsActivity self = this;
        viewInstructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manufactureName.getText().toString().equals("")){
                    new InstructionsViewer(self, vieweLayout).show();
                }else{
                    new InstructionsViewer(self, vieweLayout).show(manufactureName.getText().toString());
                }



            }
        });


    }

    public void startDialog(KillerManager.Actions actions) {

        new DialogKillerManagerBuilder().setContext(this).setAction(actions).show();

    }
}
