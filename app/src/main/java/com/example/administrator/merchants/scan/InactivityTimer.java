package com.example.administrator.merchants.scan;


import android.app.Activity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：
 */
public final class InactivityTimer {
    private static final int INACTIVITY_DELAY_SECONDS = 5 * 60;
    private final ScheduledExecutorService inactivityTimer =
            Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactory());
    private final Activity activity;
    private ScheduledFuture<?> inactivityFuture = null;

    public InactivityTimer(Activity activity) {
        this.activity = activity;
        onActivity();
    }

    public void onActivity() {
        cancel();
        inactivityFuture = inactivityTimer.schedule(new FinishListener(activity),
                INACTIVITY_DELAY_SECONDS,
                TimeUnit.SECONDS);
    }

    private void cancel() {
        if (inactivityFuture != null) {
            inactivityFuture.cancel(true);
            inactivityFuture = null;
        }
    }

    public void shutdown() {
        cancel();
        inactivityTimer.shutdown();
    }

    private static final class DaemonThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        }
    }
}
