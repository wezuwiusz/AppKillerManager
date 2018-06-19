package com.thelittlefireman.appkillermanager.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;

import com.thelittlefireman.appkillermanager.R;
import com.thelittlefireman.appkillermanager.killerManager.KillerManager;
import com.thelittlefireman.appkillermanager.utils.KillerManagerUtils;

public class DialogKillerManagerBuilder {
    private Context mContext;

    public DialogKillerManagerBuilder(){
        contentResMessage = -1;
        titleResMessage = -1;
        iconRes = -1;
    }
    public DialogKillerManagerBuilder(Context context) {
        this();
        mContext = context;
    }

    private KillerManager.Actions mAction;

    private boolean enableDontShowAgain =false;

    private String titleMessage;
    private String contentMessage;

    @DrawableRes
    private int iconRes;

    @StringRes
    private int titleResMessage, contentResMessage;

    public DialogKillerManagerBuilder setContext(Context context) {
        mContext = context;
        return this;
    }

    public DialogKillerManagerBuilder setAction(KillerManager.Actions action) {
        mAction = action;
        return this;
    }

    public DialogKillerManagerBuilder setIconRes(@NonNull @DrawableRes int iconRes) {
        this.iconRes = iconRes;
        return this;
    }

    public DialogKillerManagerBuilder setDontShowAgain(boolean enable){
        this.enableDontShowAgain= enable;
        return this;
    }

    public DialogKillerManagerBuilder setTitleMessage (@NonNull String titleMessage){
        this.titleMessage=titleMessage;
        return this;
    }

    public DialogKillerManagerBuilder setContentMessage (@NonNull String contentMessage){
        this.contentMessage=contentMessage;
        return this;
    }

    public DialogKillerManagerBuilder setTitleMessage (@StringRes @NonNull int titleResMessage){
        this.titleResMessage=titleResMessage;
        return this;
    }

    public DialogKillerManagerBuilder setContentMessage (@StringRes  @NonNull int contentResMessage){
        this.contentResMessage=contentResMessage;
        return this;
    }


    public void show(){
        if(mContext==null){
            throw new NullPointerException("Context can't be null");
        }
        if(mAction ==null){
            throw new NullPointerException("Action can't be null");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton(R.string.dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                KillerManager.doAction(mContext, mAction);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);

        if(iconRes != -1){
            builder.setIcon(iconRes);
        }else {
            builder.setIcon(android.R.drawable.ic_dialog_alert);
        }

        if(titleResMessage != -1){
            builder.setTitle(titleResMessage);
        }
        else if(titleMessage !=null && !titleMessage.isEmpty()){
            builder.setTitle(titleMessage);
        }else {
            builder.setTitle(R.string.dialog_title_huawei_notification);
        }

        if(contentResMessage != -1){
            builder.setMessage(contentResMessage);
        }
        else if(contentMessage !=null && !contentMessage.isEmpty()){
            builder.setMessage(contentMessage);
        }else {
            //TODO FAIRE EN FONCTION DU DEVICE MESSAGE CUSTOM
            builder.setMessage(String.format(mContext.getString(R.string.dialog_huawei_notification),
                                             mContext.getString(
                                                     R.string.app_name)));
        }

        if(this.enableDontShowAgain){
            AppCompatCheckBox dontShowAgain = new AppCompatCheckBox(mContext);
            dontShowAgain.setText(R.string.dialog_do_not_show_again);
            dontShowAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    KillerManagerUtils.setDontShowAgain(mContext,mAction,isChecked);
                }
            });
            builder.setView(dontShowAgain);
        }

        if(!(enableDontShowAgain && KillerManagerUtils.isDontShowAgain(mContext,mAction))){
            builder.show();
        }
    }
}