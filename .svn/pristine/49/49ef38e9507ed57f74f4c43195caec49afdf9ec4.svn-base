package com.example.administrator.merchants.common;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.merchants.activity.MerchantsOrderDetailActivity;
import com.example.administrator.merchants.activity.OriginalOrderDetailActivity;

/**
 * 作者：韩宇 on 2017/8/1 0001 10:26
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：自定义消息通知处理
 */
public class NotificationBroadcastReceiver extends BroadcastReceiver {
    public static final String TYPE = "type";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int type = intent.getIntExtra(TYPE, -1);
        String ordno = intent.getStringExtra("ordno");
        NotificationManager notificationManager = null;
        if (type != -1) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(type);
        }
        if (action.equals("notification_clicked")) {
            //处理点击事件
            Intent intent1 = new Intent();
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String s = ordno.substring(0, 4);
            intent1.putExtra("ordno", ordno);
            if (s.equals("HSHX") || s.equals("HSHP")) {
                intent1.setClass(context, MerchantsOrderDetailActivity.class);
            } else {
                intent1.setClass(context, OriginalOrderDetailActivity.class);
            }
            context.startActivity(intent1);
        }
        if (action.equals("notification_cancelled")) {
            //处理滑动清除和点击删除事件
            notificationManager.cancel(0);
        }
    }
}