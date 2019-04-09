package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.webkit.WebView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.ServiceDetailsActivity;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/2 0002 09:51
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：服务详情
 */
public class ServiceDetailListener implements Response.Listener<JSONObject> {
    private Context context;
    private WebView imageView;

    public ServiceDetailListener(Context context, WebView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                ServiceDetailsActivity.getServeId = jsonObject.getString("serveid");
                String htmlString = jsonObject.getString("servecontent");
                imageView.loadDataWithBaseURL(null, htmlString, "text/html", "UTF-8", null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失表
            Status.fail(context, jsonObject);
        }

    }
}
