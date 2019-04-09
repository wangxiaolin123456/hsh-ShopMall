

package com.example.administrator.merchants.scan;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：
 */
final class AutoFocusCallback implements Camera.AutoFocusCallback {
    private static final long AUTOFOCUS_INTERVAL_MS = 1500L;
    private Handler autoFocusHandler;
    private int autoFocusMessage;

    void setHandler(Handler autoFocusHandler, int autoFocusMessage) {
        this.autoFocusHandler = autoFocusHandler;
        this.autoFocusMessage = autoFocusMessage;
    }

    public void onAutoFocus(boolean success, Camera camera) {
        if (autoFocusHandler != null) {
            Message message = autoFocusHandler.obtainMessage(autoFocusMessage, success);
            autoFocusHandler.sendMessageDelayed(message, AUTOFOCUS_INTERVAL_MS);
            autoFocusHandler = null;
        }
    }
}
