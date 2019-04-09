package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.BankShowBean;
import com.example.administrator.merchants.adapter.BankAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONObject;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 14:18
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：设置默认银行卡
 */
public class DefaultBankCardListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<BankShowBean> list;
    private int position;
    private BankAdapter bankAdapter;

    public DefaultBankCardListener(Context context, List<BankShowBean> list, int position, BankAdapter bankAdapter) {
        this.context = context;
        this.list = list;
        this.position = position;
        this.bankAdapter = bankAdapter;

    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //请求返回成功
            for (int i = 0; i < list.size(); i++) {
                if (i == position - 1) {
                    list.get(i).setStu("1");
                } else {
                    list.get(i).setStu("0");
                }
            }
            bankAdapter.notifyDataSetChanged();
            CustomToast.getInstance(context).setMessage("设置默认银行卡成功！");


        } else {
            //请求返回失败
            Status.fail(context, jsonObject);
        }

    }
}
