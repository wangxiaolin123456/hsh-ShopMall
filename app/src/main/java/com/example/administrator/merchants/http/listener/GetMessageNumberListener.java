package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/18 0018 08:40
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：获取首页未读消息个数
 */
public class GetMessageNumberListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView messageRead;

    public GetMessageNumberListener(Context context, TextView messageRead) {
        this.context = context;
        this.messageRead = messageRead;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                if (jsonObject.getInt("count") == 0) {
                    messageRead.setVisibility(View.GONE);
                } else {
                    messageRead.setVisibility(View.VISIBLE);
                    messageRead.setText(jsonObject.getString("count"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
