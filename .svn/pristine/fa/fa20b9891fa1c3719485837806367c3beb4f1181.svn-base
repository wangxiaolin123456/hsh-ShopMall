package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/7/16 0016 08:52
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：消息详情
 */
public class MessageDetailListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView textView;

    public MessageDetailListener(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                jsonObject = jsonObject.getJSONObject("storenews");
                String dd = jsonObject.getString("newscontent");
                textView.setText("        " + dd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
