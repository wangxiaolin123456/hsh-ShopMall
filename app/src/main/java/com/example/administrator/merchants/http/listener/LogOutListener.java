package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.LoginActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Status;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/18 0018 15:25
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：推荐二维码
 */
public class LogOutListener implements Response.Listener<JSONObject> {
    private Context context;

    public LogOutListener(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            UserInfo.getInstance().setUser(null, context);
            Intent intent = new Intent();
            intent.setClass(context, LoginActivity.class);
            context.startActivity(intent);
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
