package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/18 0018 15:25
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：推荐二维码
 */
public class RevenueHeadListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView forecastEarnings, accumulatedIncome;//预估收益

    public RevenueHeadListener(Context context, TextView forecastEarnings, TextView accumulatedIncome) {
        this.context = context;
        this.forecastEarnings = forecastEarnings;
        this.accumulatedIncome = accumulatedIncome;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                String accumulatedIncomeMoney = jsonObject.getString("retbeibiamtall");//当前推荐总收益
                String forecastEarningsMoney = jsonObject.getString("preretbeibiamt");//预估推荐收益
                accumulatedIncome.setText(accumulatedIncomeMoney);
                forecastEarnings.setText(forecastEarningsMoney);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
