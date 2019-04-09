package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.OriginOrderAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.OriginOrderShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONObject;

import java.util.List;

/**
 * 作者：韩宇 on 2017/8/20 0020 10:07
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：删除订单
 */
public class OrderDeleteListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<OriginOrderShowBean> list;
    private int position;
    private OriginOrderAdapter originOrderAdapter;

    public OrderDeleteListener(Context context, List<OriginOrderShowBean> list, int position, OriginOrderAdapter originOrderAdapter) {
        this.context = context;
        this.list = list;
        this.position = position;
        this.originOrderAdapter = originOrderAdapter;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            list.remove(position - 1);
            originOrderAdapter.notifyDataSetChanged();
            CustomToast.getInstance(context).setMessage("删除成功！");
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
