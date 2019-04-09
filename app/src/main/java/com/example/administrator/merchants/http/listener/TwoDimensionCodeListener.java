package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.test.MainActivity;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/18 0018 15:25
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：推荐二维码
 */
public class TwoDimensionCodeListener implements Response.Listener<JSONObject> {
    private Context context;

    public TwoDimensionCodeListener(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                String path = jsonObject.getString("qrcpcode");
                String type = jsonObject.getString("levelno");
                String text;
                if ("2".equals(type)) {
                    text = "扫一扫二维码，下载互实会商家版APP";
                } else {
                    text = "扫一扫二维码，下载互实会APP";
                }
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.myTwoDimensionalCode(HttpUrl.BaseImageUrl + path, text);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
