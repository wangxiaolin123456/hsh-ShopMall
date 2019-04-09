package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.webkit.WebView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.EnterpriseDemandActivity;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/26 0026 17:43
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：企业需求详情
 */
public class EnterDetailsListener implements Response.Listener<JSONObject> {
    private Context context;
    private WebView imageView;//web背景显示

    public EnterDetailsListener(Context context, WebView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        LogUtil.i("需求返回"+jsonObject.toString());
        if (Status.status(jsonObject)) {
            //返回成功
            String htmlString = null;
            try {
                htmlString = jsonObject.getString("servecontent");
                EnterpriseDemandActivity.getServeid = jsonObject.getString("serveid");
                imageView.loadDataWithBaseURL(null, htmlString, "text/html",
                        "UTF-8", null);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
