package com.example.administrator.merchants.common.toast;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：toast
 */
public class TimerCountToast extends CountDownTimer {
    private Dialog dialog;
    private Context mContext;
    private int type;

    public TimerCountToast(long millisInFuture, long countDownInterval, Dialog dialog, int type) {
        super(millisInFuture, countDownInterval);
        this.dialog = dialog;
        this.type = type;
    }

    public TimerCountToast(long millisInFuture, long countDownInterval, Dialog dialog, Context mContext, int type) {
        super(millisInFuture, countDownInterval);
        this.dialog = dialog;
        this.mContext = mContext;
        this.type = type;
    }

    @Override
    public void onFinish() {
        dialog.dismiss();
        if (type == 2) {
            ((Activity) mContext).finish();
        }
    }

    @Override
    public void onTick(long arg0) {
    }
}
