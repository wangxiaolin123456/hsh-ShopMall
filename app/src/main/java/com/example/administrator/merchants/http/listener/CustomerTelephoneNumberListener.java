package com.example.administrator.merchants.http.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/18 0018 15:54
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：客服电话
 */
public class CustomerTelephoneNumberListener implements Response.Listener<JSONObject> {
    private Context context;

    public CustomerTelephoneNumberListener(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                JSONObject tel = jsonObject.getJSONObject("systeminfo");
                final String tell = tel.getString("infovalue");
                new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("\n\t\t\t\t\t\t\t\t" + tell + "\n\n" + "\t\t\t\t\t\t\t\t\t\t是否拨打？\n")
                        .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tell));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
