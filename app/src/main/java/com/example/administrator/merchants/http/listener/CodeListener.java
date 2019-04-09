package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.EnterpriseDemandActivity;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.TimerCount;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/26 0026 13:15
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：获取验证码
 */
public class CodeListener implements Response.Listener<JSONObject> {
    private Context context;
    private TimerCount timerCount;

    public CodeListener(Context context, TimerCount timerCount) {
        this.context = context;
        this.timerCount = timerCount;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            EnterpriseDemandActivity.st = 1;
            CustomToast.getInstance(context).setMessage("发送成功！");
            timerCount.start();
            try {
                EnterpriseDemandActivity.httpCode = jsonObject.getString("aucode");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
