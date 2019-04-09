package com.example.administrator.merchants.scan;


import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：
 */
final class PreviewCallback implements Camera.PreviewCallback {
    private final CameraConfigurationManager configManager;
    private final boolean useOneShotPreviewCallback;
    private Handler previewHandler;
    private int previewMessage;

    PreviewCallback(CameraConfigurationManager configManager, boolean useOneShotPreviewCallback) {
        this.configManager = configManager;
        this.useOneShotPreviewCallback = useOneShotPreviewCallback;
    }

    void setHandler(Handler previewHandler, int previewMessage) {
        this.previewHandler = previewHandler;
        this.previewMessage = previewMessage;
    }

    public void onPreviewFrame(byte[] data, Camera camera) {
        Point cameraResolution = configManager.getCameraResolution();
        if (!useOneShotPreviewCallback) {
            camera.setPreviewCallback(null);
        }
        if (previewHandler != null) {
            Message message = previewHandler.obtainMessage(previewMessage, cameraResolution.x,
                    cameraResolution.y, data);
            message.sendToTarget();
            previewHandler = null;
        } else {
        }
    }
}
