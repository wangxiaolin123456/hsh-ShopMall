package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.SellOrderAdapter;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.fragment.MerchantOrderFragment;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.OrderShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/18 0018 10:54
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家订单列表
 */
public class MerchantOrderListListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private List<OrderShowBean> list;
    private ImageView anim;
    private ListView listView;
    private SellOrderAdapter orderAdapter;
    private RefreshLayout swipeLayout;
    private View footView;
    private RefreshLayout.OnLoadListener onLoadListener;

    public MerchantOrderListListener(Context context, int type, List<OrderShowBean> list, ImageView anim, ListView listView, SellOrderAdapter orderAdapter
            , RefreshLayout swipeLayout, View footView, RefreshLayout.OnLoadListener onLoadListener) {
        this.context = context;
        this.type = type;
        this.onLoadListener=onLoadListener;
        this.anim = anim;
        this.swipeLayout = swipeLayout;
        this.listView = listView;
        this.list = list;
        this.footView = footView;
        this.orderAdapter = orderAdapter;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            if (type == 0 || type == 2) {
                list.clear();
            }
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> objectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    objectList.add(jsonObject1);
                }
                for (int j = 0; j < objectList.size(); j++) {
                    OrderShowBean orderShowBean = new OrderShowBean();
                    orderShowBean.setOrdtype(objectList.get(j).getString("ordtype"));//外卖与否
                    orderShowBean.setOrdstatus(objectList.get(j).getString("ordstatus"));//完成与否
                    orderShowBean.setPaystatus(objectList.get(j).getString("paystatus"));//付款与否
                    orderShowBean.setOrdno(objectList.get(j).getString("ordno"));
                    orderShowBean.setCreatetimestr(objectList.get(j).getString("createtimestr"));
                    orderShowBean.setOrdmerqty(objectList.get(j).getInt("ordmerqty"));
                    orderShowBean.setOrdmername(objectList.get(j).getString("ordmername"));
                    orderShowBean.setSendstatus(objectList.get(j).getString("sendstatus"));
                    orderShowBean.setStationid(objectList.get(j).getString("stationid"));
                    orderShowBean.setOrdimgsfile(HttpUrl.BaseImageUrl + objectList.get(j).getString("ordimgsfile"));
                    list.add(orderShowBean);
                }
                if (type == 0) {
                    GlideTest.imageCancle(anim);
                    listView.setAdapter(orderAdapter);
                    swipeLayout.setRefreshing(false);
                } else if (type == 1) {
                    orderAdapter.notifyDataSetChanged();
                    MerchantOrderFragment.s = 1;
                    swipeLayout.setLoading(false);
                } else if (type == 2) {
                    orderAdapter.notifyDataSetChanged();
                }
                if (objectList.size() < 15) {
                    swipeLayout.setOnLoadListener(null);
                    listView.removeFooterView(footView);
                    listView.addFooterView(footView);
                } else {
                    swipeLayout.setOnLoadListener(onLoadListener);
                    listView.removeFooterView(footView);
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
