package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.SellOrderAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.OrderShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 作者：韩宇 on 2017/8/11 0011 08:47
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：列表中的配送
 */
public class SendListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<OrderShowBean> list;
    private SellOrderAdapter sellOrderAdapter;
    private int position;

    public SendListener(Context context, List<OrderShowBean> list, SellOrderAdapter sellOrderAdapter, int position) {
        this.context = context;
        this.list = list;
        this.sellOrderAdapter = sellOrderAdapter;
        this.position = position;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功

            try {
                list.get(position).setSendstatus("1");
                sellOrderAdapter.notifyDataSetChanged();
                CustomToast.getInstance(context).setMessage(jsonObject.getString("已发货！"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
