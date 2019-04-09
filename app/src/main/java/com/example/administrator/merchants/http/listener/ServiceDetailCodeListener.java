package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.Button;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.ServiceDetailsActivity;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.TimerCount;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/2 0002 09:34
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：提交服务申请获取验证码
 */
public class ServiceDetailCodeListener implements Response.Listener<JSONObject> {
    private Context context;
    private TimerCount timerCount;
    private Button getCode;

    public ServiceDetailCodeListener(Context context, TimerCount timerCount, Button getCode) {
        this.context = context;
        this.getCode = getCode;
        this.timerCount = timerCount;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            CustomToast.getInstance(context).setMessage("发送成功！");
            timerCount.start();
            ServiceDetailsActivity.st = 1;
            try {
                ServiceDetailsActivity.httpCode = jsonObject.getString("aucode");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
