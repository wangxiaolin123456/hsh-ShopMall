package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/10 0010 14:56
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：微信回调
 */
public class WXPayEntryListener implements Response.Listener<JSONObject>{
    private Context context;
    public WXPayEntryListener(Context context){
        this.context=context;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)){
            //返回成功
            Log.e("@@@@@@@@@@@@@@", "支付成功！");
        }else {
            //返回失败
            Status.fail(context,jsonObject);
        }

    }
}
