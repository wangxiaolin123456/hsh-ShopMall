package com.example.administrator.merchants.scan;

import android.app.Activity;
import android.content.DialogInterface;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：
 */
public final class FinishListener
        implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable {
    private final Activity activityToFinish;

    public FinishListener(Activity activityToFinish) {
        this.activityToFinish = activityToFinish;
    }

    public void onCancel(DialogInterface dialogInterface) {
        run();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        run();
    }

    public void run() {
        activityToFinish.finish();
    }
}
