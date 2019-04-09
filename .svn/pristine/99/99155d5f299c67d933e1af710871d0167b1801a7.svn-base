package com.example.administrator.merchants.jpush;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.AccountStatementDetailsActivity;
import com.example.administrator.merchants.activity.BeiBiRecordActivity;
import com.example.administrator.merchants.activity.EstimatedEarningsDetailsActivity;
import com.example.administrator.merchants.activity.MerchantsOrderDetailActivity;
import com.example.administrator.merchants.activity.MyMessageListActivity;
import com.example.administrator.merchants.activity.NoticeDetailsActivity;
import com.example.administrator.merchants.activity.OriginalOrderDetailActivity;
import com.example.administrator.merchants.common.NotificationBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者：韩宇 on 2017/8/19 0019 16:46
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：自定义广播
 */
public class MeReceiver extends BroadcastReceiver {
    public static String ordNo = null;
    private static int type = 0;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(final Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            try {
                JSONObject myJsonObject = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                ordNo = myJsonObject.getString("ordno");
                type = Integer.parseInt(myJsonObject.getString("newstype"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (10 == type) {
                NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(context);
                builder.setAutoCancel(true);
                builder.setSmallIcon(R.drawable.jpush_image);
                builder.setContentTitle("互实会");
                builder.setContentText(bundle.getString(JPushInterface.EXTRA_MESSAGE));
                builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
                Intent intents = new Intent();
                intents.putExtra("noticeid", ordNo);
                intents.setClass(context, NoticeDetailsActivity.class);
                intents.addCategory(Intent.CATEGORY_LAUNCHER);
                PendingIntent pIntent = PendingIntent.getActivity(context, 1, intents, PendingIntent.FLAG_ONE_SHOT);
                builder.setContentIntent(pIntent);
                manager.notify(0, builder.build());
                return;
            } else if (type == 97 || type == 98) {
                NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                Notification.Builder
                        builder = new Notification.Builder(context);
                builder.setAutoCancel(true);
                builder.setSmallIcon(R.drawable.jpush_image);
                builder.setContentTitle("互实会");
                builder.setContentText("有人赠与您贝币请查看");
                builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
                Intent intents = new Intent();
                intents.setClass(context, BeiBiRecordActivity.class);
                intents.addCategory(Intent.CATEGORY_LAUNCHER);
                PendingIntent pIntent = PendingIntent.getActivity(context, 1, intents, PendingIntent.FLAG_ONE_SHOT);
                builder.setContentIntent(pIntent);
                manager.notify(0, builder.build());
                return;
            } else if (4 == type) {
                NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(context);
                builder.setAutoCancel(true);
                builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.money));
                builder.setSmallIcon(R.drawable.jpush_image);
                builder.setContentTitle("互实会");
                builder.setContentText(bundle.getString(JPushInterface.EXTRA_MESSAGE));
                Intent intents = new Intent();
                intents.putExtra("settleno", ordNo);
                intents.setClass(context, AccountStatementDetailsActivity.class);
                intents.addCategory(Intent.CATEGORY_LAUNCHER);
                PendingIntent pIntent = PendingIntent.getActivity(context, 1, intents, PendingIntent.FLAG_ONE_SHOT);
                builder.setContentIntent(pIntent);
                manager.notify(0, builder.build());
            } else if (type == 5) {
                Intent intentClick = new Intent(context, NotificationBroadcastReceiver.class);
                intentClick.setAction("notification_clicked");
                intentClick.putExtra("ordno", ordNo);
                intentClick.putExtra(NotificationBroadcastReceiver.TYPE, 0);
                PendingIntent pendingIntentClick = PendingIntent.getBroadcast(context, 0, intentClick, PendingIntent.FLAG_ONE_SHOT);
                Intent intentCancel = new Intent(context, NotificationBroadcastReceiver.class);
                intentCancel.setAction("notification_cancelled");
                intentCancel.putExtra(NotificationBroadcastReceiver.TYPE, 0);
                PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(context, 0, intentCancel, PendingIntent.FLAG_ONE_SHOT);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
                notificationBuilder.setAutoCancel(true)
                        .setSmallIcon(R.drawable.jpush_image)
                        .setContentTitle("互实会")
                        .setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.order))
                        .setContentText(bundle.getString(JPushInterface.EXTRA_MESSAGE))
                        .setContentIntent(pendingIntentClick)
                        .setDeleteIntent(pendingIntentCancel);
                NotificationManager tinotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = notificationBuilder.build();
                notification.flags = Notification.FLAG_INSISTENT;
                tinotificationManager.notify(0, notification);
            } else if (6 == type) {
                NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(context);
                builder.setAutoCancel(true);
                builder.setSmallIcon(R.drawable.jpush_image);
                builder.setContentTitle("互实会");
                builder.setContentText(bundle.getString(JPushInterface.EXTRA_MESSAGE));
                builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
                Intent intents = new Intent();
                intents.putExtra("texu", "tuisong");
                intents.setClass(context, EstimatedEarningsDetailsActivity.class);
                intents.addCategory(Intent.CATEGORY_LAUNCHER);
                PendingIntent pIntent = PendingIntent.getActivity(context, 1, intents, PendingIntent.FLAG_ONE_SHOT);
                builder.setContentIntent(pIntent);
                manager.notify(0, builder.build());
            } else {
                if ("".equals(ordNo)) {
                    NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                    Notification.Builder builder = new Notification.Builder(context);
                    builder.setAutoCancel(true);
                    builder.setSmallIcon(R.drawable.jpush_image);
                    builder.setContentTitle("互实会");
                    builder.setContentText(bundle.getString(JPushInterface.EXTRA_MESSAGE));
                    builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
                    Intent intents = new Intent();
                    intents.setClass(context, MyMessageListActivity.class);
                    intents.addCategory(Intent.CATEGORY_LAUNCHER);
                    PendingIntent pIntent = PendingIntent.getActivity(context, 1, intents, PendingIntent.FLAG_ONE_SHOT);
                    builder.setContentIntent(pIntent);
                    manager.notify(0, builder.build());
                    return;
                } else {
                    NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                    Notification.Builder builder = new Notification.Builder(context);
                    builder.setAutoCancel(true);
                    builder.setSmallIcon(R.drawable.jpush_image);
                    builder.setContentTitle("互实会");
                    builder.setContentText(bundle.getString(JPushInterface.EXTRA_MESSAGE));
                    builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
                    Intent intents = new Intent();
                    intents.putExtra("ordno", ordNo);
                    String s = ordNo.substring(0, 4);
                    if (s.equals("HSHX") || s.equals("HSHP")) {
                        intents.setClass(context, MerchantsOrderDetailActivity.class);
                    } else {
                        intents.setClass(context, OriginalOrderDetailActivity.class);
                    }
                    intents.addCategory(Intent.CATEGORY_LAUNCHER);
                    PendingIntent pIntent = PendingIntent.getActivity(context, 1, intents, PendingIntent.FLAG_ONE_SHOT);
                    builder.setContentIntent(pIntent);
                    manager.notify(0, builder.build());
                }
            }
        }
    }
}
