package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/7/3 0003 11:36
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：互实攻略
 */
public class StrategyListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView strategyText;
    public StrategyListener(Context context,TextView strategyText){
        this.context=context;
        this.strategyText=strategyText;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)){
            //返回成功
            JSONObject object = null;
            try {
                object = jsonObject.getJSONObject("systeminfo");
                String ss = object.getString("infovalue");
                strategyText.setText(ss);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else {
            //返回失败
            Status.fail(context,jsonObject);
        }

    }
}
