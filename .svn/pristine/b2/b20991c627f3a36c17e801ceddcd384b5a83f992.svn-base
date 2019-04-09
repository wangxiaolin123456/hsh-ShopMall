package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/17 0017 09:37
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：购物车商品数量
 */
public class ShoppingCarCountListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView textViewDotCar;

    public ShoppingCarCountListener(Context context, TextView textViewDotCar) {
        this.context = context;
        this.textViewDotCar = textViewDotCar;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                int merqty = jsonObject.getInt("merqty");
                textViewDotCar.setText(String.valueOf(merqty));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
            textViewDotCar.setText("0");
        }

    }
}
