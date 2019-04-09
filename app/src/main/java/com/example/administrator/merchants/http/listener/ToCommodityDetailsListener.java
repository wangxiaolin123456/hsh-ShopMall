package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.GoodsDetailsActivity;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;

import org.json.JSONObject;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/18 0018 13:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：跳转原产地商品详情
 */
public class ToCommodityDetailsListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<MerchantsOrderShowBean> list;
    private int position;

    public ToCommodityDetailsListener(Context context, List<MerchantsOrderShowBean> list, int position) {
        this.context = context;
        this.list = list;
        this.position = position;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            Intent intent = new Intent();
            intent.putExtra("merid", list.get(position - 1).getMerid());
            intent.setClass(context, GoodsDetailsActivity.class);//CommodityDetailsActivity
            context.startActivity(intent);
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
