package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.OrderShowBean;
import com.example.administrator.merchants.activity.AccountStatementDetailsActivity;
import com.example.administrator.merchants.adapter.SellOrderAdapter;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：对账单明细列表post参数
 */
public class AccountStatementDetailListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private List<OrderShowBean> list;
    private ListView listView;
    private SellOrderAdapter orderAdapter;
    private RefreshLayout swipeLayout;
    private View footView;

    public AccountStatementDetailListener(Context context, int type, List<OrderShowBean> list, ListView listView, SellOrderAdapter orderAdapter, RefreshLayout swipeLayout, View footView) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.listView = listView;
        this.orderAdapter = orderAdapter;
        this.swipeLayout = swipeLayout;
        this.footView = footView;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            JSONArray jsonArray = null;
            try {
                if (type == 0) {//刷新清空集合
                    list.clear();
                }
                jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> objectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {

                    objectList.add((JSONObject) jsonArray.get(i));
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
                    swipeLayout.setRefreshing(false);//刷新停止
                    listView.setAdapter(orderAdapter);
                } else {
                    AccountStatementDetailsActivity.s = 1;
                    swipeLayout.setLoading(false);//加载停止
                    orderAdapter.notifyDataSetChanged();
                }
                if (objectList.size() < 15) {
                    swipeLayout.setOnLoadListener(null);
                    listView.removeFooterView(footView);
                    listView.addFooterView(footView);
                } else {
                    swipeLayout.setOnLoadListener((RefreshLayout.OnLoadListener) context);
                    listView.removeFooterView(footView);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {
            Status.fail(context, jsonObject);
        }

    }
}
