package com.example.administrator.merchants.common;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：计时器继承类
 */
public abstract class CountDownTimer {
    private final long mMillisInFuture;
    private final long mCountdownInterval;
    private long mStopTimeInFuture;
    private boolean mCancelled = false;

    public CountDownTimer(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
    }

    public final void cancel() {
        mHandler.removeMessages(MSG);
        mCancelled = true;
    }

    public synchronized final CountDownTimer start() {
        if (mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        mCancelled = false;
        return this;
    }

    public abstract void onTick(long millisUntilFinished);

    public abstract void onFinish();

    private static final int MSG = 1;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            synchronized (CountDownTimer.this) {
                final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();
                if (millisLeft <= 0) {
                    onFinish();
                } else if (millisLeft < mCountdownInterval) {
                    sendMessageDelayed(obtainMessage(MSG), millisLeft);
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(millisLeft);
                    long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();
                    while (delay < 0) delay += mCountdownInterval;
                    if (!mCancelled) {
                        sendMessageDelayed(obtainMessage(MSG), delay);
                    }
                }
            }
        }
    };
}
