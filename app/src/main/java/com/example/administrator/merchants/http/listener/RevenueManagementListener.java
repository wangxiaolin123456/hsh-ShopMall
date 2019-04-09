package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/25 0025 13:44
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：收益管理数据展示
 */
public class RevenueManagementListener implements Response.Listener<JSONObject> {
    public Context context;
    private TextView accumulatedIncomeMoney;//累计收益金额
    private TextView estimatedEarningsMoney;//预估收益金额
    private TextView recommendedNumber;//推荐人数
    private int type;

    public RevenueManagementListener(Context context, TextView accumulatedIncomeMoney, TextView estimatedEarningsMoney, TextView recommendedNumber, int type) {
        this.context = context;
        this.accumulatedIncomeMoney = accumulatedIncomeMoney;
        this.estimatedEarningsMoney = estimatedEarningsMoney;
        this.recommendedNumber = recommendedNumber;
        this.type = type;
    }

    public RevenueManagementListener(Context context, TextView estimatedEarningsMoney, int type) {
        this.context = context;
        this.estimatedEarningsMoney = estimatedEarningsMoney;
        this.type = type;

    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功！
            try {
                if (type == 0) {
                    String accumulatedIncomeMoneyDate = jsonObject.getString("retbeibiamtall");  //当前推荐总收益
                    String estimatedEarningsMoneyDate = jsonObject.getString("preretbeibiamt");//预估推荐收益
                    accumulatedIncomeMoney.setText(accumulatedIncomeMoneyDate);
                    estimatedEarningsMoney.setText(estimatedEarningsMoneyDate);
                    String recommendedNumberDate = jsonObject.getString("hslnum");//推荐人数
                    recommendedNumber.setText(recommendedNumberDate);
                } else if (type == 1) {
                    String preretbeibiamt = jsonObject.getString("preretbeibiamt");//预估推荐收益
                    estimatedEarningsMoney.setText(preretbeibiamt);
                }else if (type==2){
                    String retbeibiamtall = jsonObject.getString("retbeibiamtall");//当前推荐总收益
                    estimatedEarningsMoney.setText(retbeibiamtall);
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
