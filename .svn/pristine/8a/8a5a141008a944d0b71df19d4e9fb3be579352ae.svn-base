package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/17 0017 09:19
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商品添加购物车
 */
public class AddShopCarListener implements Response.Listener<JSONObject> {
    private Context context;

    public AddShopCarListener(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            Toast.makeText(context, "亲*已加入购物车请到购物车结算！", Toast.LENGTH_LONG).show();
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
